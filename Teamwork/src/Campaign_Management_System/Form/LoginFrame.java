package Campaign_Management_System.Form;

import Campaign_Management_System.Entity.User;
import Campaign_Management_System.Service.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LoginFrame extends JFrame {
    private final JTextField nameField; // 姓名输入框
    private final JPasswordField passwordField; // 密码输入框
    private UserServiceImpl userService = new UserServiceImpl();

    public LoginFrame() {
        setTitle("用户登录"); // 设置窗口标题
        setSize(300, 200); // 设置窗口大小
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置窗口关闭操作
        setLocationRelativeTo(null); // 设置窗口位置（在屏幕中央）
        setLayout(new BorderLayout()); // 设置布局

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 3, 3)); // 创建表单面板
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // 设置面板边距

        // 添加姓名输入框
        formPanel.add(new JLabel("姓名:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        // 添加密码输入框
        formPanel.add(new JLabel("密码:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        // 添加按钮
        JButton loginButton = new JButton("登录");
        JButton cancelButton = new JButton("取消");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH); // 将按钮面板添加到窗口底部
        add(formPanel, BorderLayout.NORTH);
        // 添加动作监听器
        loginButton.addActionListener(e -> {
            if (validateInputs()) { // 验证表
                ; //登录成功菜单界面
            }
        });

        cancelButton.addActionListener(e -> System.exit(0)); // 点击取消按钮退出程序

        setVisible(true); // 显示窗口
    }

    private boolean validateInputs() {

        if (nameField.getText().isEmpty() || passwordField.getPassword().length == 0) {
            // 如果任何一个输入框为空，则弹出错误提示框
            JOptionPane.showMessageDialog(this, "请填写所有内容", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (userService.login(nameField.getText(), String.valueOf(passwordField.getPassword()))) {
            JOptionPane.showMessageDialog(this, "登录成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            if (userService.getUserByUsername(nameField.getText()).getIs_judge()) {
                new JudgeFrame();
                //关闭当前窗口
                dispose();
            } else if (Objects.equals(userService.getUserByUsername(nameField.getText()).getRole(), "管理员")) {
                new AdminFrame();
                dispose();
            } else {
                new UserFrame(userService.getUserByUsername(nameField.getText()));
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
