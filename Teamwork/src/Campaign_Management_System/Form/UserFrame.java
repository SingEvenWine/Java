package Campaign_Management_System.Form;

import Campaign_Management_System.Entity.Participant;
import Campaign_Management_System.Entity.User;
import Campaign_Management_System.Service.ParticipantService;
import Campaign_Management_System.Service.ParticipantServicelmpl;
import Campaign_Management_System.Service.SportsEventServicelmpl;
import Campaign_Management_System.Service.UserServiceImpl;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserFrame extends JFrame {
    UserServiceImpl userService = new UserServiceImpl();
    SportsEventServicelmpl sportsEventService = new SportsEventServicelmpl();
    ParticipantServicelmpl participantService = new ParticipantServicelmpl();
    // 创建菜单栏
    JMenuBar menuBar = new JMenuBar();
    // 创建菜单
    JMenu menu1 = new JMenu("查看信息");
    JMenu menu2 = new JMenu("报名比赛");
    JMenu menu3 = new JMenu("查看成绩");

    public UserFrame(User user) {

        setTitle("用户界面"); // 设置窗口标题
        setSize(500, 350); // 设置窗口大小
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置窗口关闭操作
        setLocationRelativeTo(null); // 设置窗口位置（在屏幕中央）
        setLayout(new BorderLayout()); // 设置布局

        // 添加菜单到菜单栏
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        // 添加菜单栏到窗口
        setJMenuBar(menuBar);

        menu1.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {

                // 添加姓名输入框
                JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5)); // 创建表单面板
                formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 设置面板边距
                formPanel.add(new JLabel("姓名:"));
                JTextField nameField = new JTextField();
                nameField.setText(user.getUsername());
                nameField.setFocusable(false);
                formPanel.add(nameField);
                // 添加性别输入框
                formPanel.add(new JLabel("性别:"));
                JTextField sexField = new JTextField();
                sexField.setText(user.isMan() ? "男" : "女");
                sexField.setFocusable(false);
                formPanel.add(sexField);
                // 添加密码输入框
                formPanel.add(new JLabel("密码:"));
                JTextField ageField = new JTextField();
                ageField.setText(String.valueOf(user.getPassword()));
                ageField.setFocusable(false);
                formPanel.add(ageField);
                // 添加身份输入框
                formPanel.add(new JLabel("身份:"));
                JTextField rolesField = new JTextField();
                rolesField.setText(user.getRole());
                rolesField.setFocusable(false);
                formPanel.add(rolesField);

                // 添加按钮
                JButton updateButton = new JButton("修改");
                JButton cancelButton = new JButton("取消");

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.add(updateButton);
                buttonPanel.add(cancelButton);

                // 添加表单和按钮到窗口
                add(formPanel, BorderLayout.CENTER);
                add(buttonPanel, BorderLayout.SOUTH);

                // 添加动作监听器
                cancelButton.addActionListener(e1 -> {
                    setVisible(false);
                    new UserFrame(user);
                }); // 点击取消按钮退出程序

                updateButton.addActionListener(e12 -> {
                    JButton saveButton = new JButton("保存");
                    buttonPanel.add(saveButton);
                    // 在这里执行修改逻辑代码
                    nameField.setFocusable(true);
                    sexField.setFocusable(true);
                    ageField.setFocusable(true);
                    rolesField.setFocusable(true);
                    saveButton.addActionListener(e121 -> {
                        setVisible(false);
                        new UserFrame(user);//保存逻辑代码
                    }); // 点击保存按钮退出程序\
                    setVisible(true);
                }); // 点击修改按钮转到登录界面

                setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        menu2.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                List<JRadioButton> radioButtons = new ArrayList<>();
                sportsEventService.getAllEvents().forEach(sportsEvent -> {
                    JRadioButton radioButton = new JRadioButton(sportsEvent.getEventName());
                    radioButtons.add(radioButton);
                });
                JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5)); // 创建表单面板
                formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 设置面板边距
                radioButtons.forEach(radioButton -> {
                    formPanel.add(radioButton);
                });
                JButton cancelButton = new JButton("取消");
                JButton saveButton = new JButton("保存");
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.add(saveButton);
                buttonPanel.add(cancelButton);
                add(formPanel, BorderLayout.CENTER);
                add(buttonPanel, BorderLayout.SOUTH);
                cancelButton.addActionListener(e1 -> {
                    setVisible(false);
                    new UserFrame(user);
                }); // 点击取消按钮退出程序
                saveButton.addActionListener(e12 -> {
                    radioButtons.forEach(radioButton -> {
                        if (radioButton.isSelected()) {
                            participantService.addParticipant(new Participant(user, sportsEventService.getEventByName(radioButton.getText())));
                        }
                    });
                    setVisible(false);
                    new UserFrame(user);//保存逻辑代码
                }); // 点击保存按钮退出程序\
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        // 设置窗口可见
        setVisible(true);

    }
    public static void main(String[] args) { // 主方法
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserFrame frame = new UserFrame(new User("张三","123456",true,"学生",false,false));
                frame.setVisible(true);
            }
        });
    }
}
