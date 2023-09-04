package Campaign_Management_System.Form;

import Campaign_Management_System.Entity.User;
import Campaign_Management_System.Service.UserServiceImpl;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private final JTextField nameField; // 姓名输入框
    private final JPasswordField passwordField; // 密码输入框
    private final JPasswordField passwordField2; // 密码输入框
    private final JCheckBox termsCheckbox; // 同意协议复选框
    private final JRadioButton teacherButton; // 教师单选框
    private final JRadioButton studentButton; // 学生单选框
    private final JRadioButton maleRadioButton; // 男性单选框
    private final JRadioButton femaleRadioButton; // 女性单选框
    private UserServiceImpl userService = new UserServiceImpl();

    public RegisterFrame() {
        setTitle("用户注册"); // 设置窗口标题
        setSize(500, 350); // 设置窗口大小
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置窗口关闭操作
        setLocationRelativeTo(null); // 设置窗口位置（在屏幕中央）
        setLayout(new BorderLayout()); // 设置布局

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5)); // 创建表单面板
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 设置面板边距

        // 添加姓名输入框
        formPanel.add(new JLabel("姓名:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        // 添加密码输入框
        formPanel.add(new JLabel("密码:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        // 添加密码输入框
        formPanel.add(new JLabel("再次输入密码:"));
        passwordField2 = new JPasswordField();
        formPanel.add(passwordField2);

        //添加身份单选框
        formPanel.add(new JLabel("身份"));
        JPanel genderPanelRoles = new JPanel();
        teacherButton = new JRadioButton("教师");
        studentButton = new JRadioButton("学生");
        ButtonGroup genderGroupRoles = new ButtonGroup(); // 创建单选按钮组
        genderGroupRoles.add(teacherButton); // 将男性单选按钮添加到单选按钮组中
        genderGroupRoles.add(studentButton); // 将女性单选按钮添加到单选按钮组中
        genderPanelRoles.add(teacherButton); // 将男性单选按钮添加到面板中
        genderPanelRoles.add(studentButton); // 将女性单选按钮添加到面板中
        formPanel.add(genderPanelRoles);

        // 添加性别单选框
        formPanel.add(new JLabel("性别:"));
        JPanel genderPanel = new JPanel();
        maleRadioButton = new JRadioButton("男");
        femaleRadioButton = new JRadioButton("女");
        ButtonGroup genderGroup = new ButtonGroup(); // 创建单选按钮组
        genderGroup.add(maleRadioButton); // 将男性单选按钮添加到单选按钮组中
        genderGroup.add(femaleRadioButton); // 将女性单选按钮添加到单选按钮组中
        genderPanel.add(maleRadioButton); // 将男性单选按钮添加到面板中
        genderPanel.add(femaleRadioButton); // 将女性单选按钮添加到面板中
        formPanel.add(genderPanel);

        // 添加同意协议复选框
        termsCheckbox = new JCheckBox("我接受用户协议");
        formPanel.add(termsCheckbox);

        add(formPanel, BorderLayout.CENTER); // 将表单面板添加到窗口中心

        // 添加按钮
        JButton registerButton = new JButton("注册");
        JButton cancelButton = new JButton("取消");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH); // 将按钮面板添加到窗口底部

        // 添加动作监听器
        registerButton.addActionListener(e -> {
            if (validateInputs()) { // 验证表
                showConfirmationMessage(); // 显示确认信息
                userService.registerUser(new User(nameField.getText(), passwordField.getText(),maleRadioButton.isSelected(), teacherButton.isSelected()?"teacher":"student", false, false));
            }
        });

        cancelButton.addActionListener(e -> System.exit(0)); // 点击取消按钮退出程序

        setVisible(true); // 显示窗口
    }

    private boolean validateInputs() { // 验证表单输入是否正确
        if (nameField.getText().isEmpty() || passwordField.getPassword().length == 0) {
            // 如果任何一个输入框为空，则弹出错误提示框
            JOptionPane.showMessageDialog(this, "请填写所有内容", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected()) {
            // 如果性别未选择，则弹出错误提示框
            JOptionPane.showMessageDialog(this, "请选择你的性别.", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!termsCheckbox.isSelected()) {
            // 如果未同意协议，则弹出错误提示框
            JOptionPane.showMessageDialog(this, "请接受我们的用户协议.", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!passwordField.getText().equals(passwordField2.getText())) {
            JOptionPane.showMessageDialog(this, "两次密码不一致.", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (userService.getUserByUsername(nameField.getText()) != null) {
            JOptionPane.showMessageDialog(this, "用户名已存在.", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true; // 输入验证通过
    }

    private void showConfirmationMessage() { // 显示确认信息
        String gender = maleRadioButton.isSelected() ? "男" : "女"; // 获取性别
        String role = teacherButton.isSelected() ? "教师" : "学生";
        String message = "用户信息:\n\n" // 生成确认信息文本
                + "姓名: " + nameField.getText() + "\n"
                + "性别: " + gender + "\n"
                + "身份: " + role + "\n";

        JOptionPane.showMessageDialog(this, message, "注册成功", JOptionPane.INFORMATION_MESSAGE); // 弹出确认信息提示框
        clearInputs(); // 清空输入框
        new LoginFrame();
        setVisible(false);
    }

    private void clearInputs() { // 清空输入框
        nameField.setText("");
        passwordField.setText("");
        passwordField2.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        teacherButton.setSelected(false);
        studentButton.setSelected(false);
        termsCheckbox.setSelected(false);
    }

    public static void main(String[] args) { // 主方法
        System.setProperty("sun.java2d.noddraw", "true"); // 禁用硬件加速，防止显示异常

        SwingUtilities.invokeLater(RegisterFrame::new); // 在事件分派线程中创建窗口
    }
}
