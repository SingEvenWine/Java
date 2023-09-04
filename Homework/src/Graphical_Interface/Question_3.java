package Graphical_Interface;

import javax.swing.*;
import java.awt.*;

public class Question_3 extends JFrame {
    private final JTextField nameField; // 姓名输入框
    private final JTextField emailField; // 邮箱输入框
    private final JPasswordField passwordField; // 密码输入框
    private final JTextArea addressArea; // 地址输入框
    private final JCheckBox termsCheckbox; // 同意协议复选框
    private final JRadioButton maleRadioButton; // 男性单选框
    private final JRadioButton femaleRadioButton; // 女性单选框

    public Question_3() {
        setTitle("User Registration"); // 设置窗口标题
        setSize(500, 350); // 设置窗口大小
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置窗口关闭操作
        setLocationRelativeTo(null); // 设置窗口位置（在屏幕中央）
        setLayout(new BorderLayout()); // 设置布局

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5)); // 创建表单面板
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 设置面板边距

        // 添加姓名输入框
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        // 添加邮箱输入框
        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        // 添加密码输入框
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        // 添加性别单选框
        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup(); // 创建单选按钮组
        genderGroup.add(maleRadioButton); // 将男性单选按钮添加到单选按钮组中
        genderGroup.add(femaleRadioButton); // 将女性单选按钮添加到单选按钮组中
        genderPanel.add(maleRadioButton); // 将男性单选按钮添加到面板中
        genderPanel.add(femaleRadioButton); // 将女性单选按钮添加到面板中
        formPanel.add(genderPanel);

        // 添加地址输入框
        formPanel.add(new JLabel("Address:"));
        addressArea = new JTextArea(3, 20);
        JScrollPane addressScrollPane = new JScrollPane(addressArea);
        formPanel.add(addressScrollPane);

        // 添加同意协议复选框
        termsCheckbox = new JCheckBox("I accept the terms and conditions");
        formPanel.add(termsCheckbox);

        add(formPanel, BorderLayout.CENTER); // 将表单面板添加到窗口中心

        // 添加按钮
        JButton registerButton = new JButton("Register");
        JButton cancelButton = new JButton("Cancel");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH); // 将按钮面板添加到窗口底部

        // 添加动作监听器
        registerButton.addActionListener(e -> {
            if (validateInputs()) { // 验证表
                showConfirmationMessage(); // 显示确认信息
            }
        });

        cancelButton.addActionListener(e -> System.exit(0)); // 点击取消按钮退出程序

        setVisible(true); // 显示窗口
    }

    private boolean validateInputs() { // 验证表单输入是否正确
        if (nameField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getPassword().length == 0 || addressArea.getText().isEmpty()) {
            // 如果任何一个输入框为空，则弹出错误提示框
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected()) {
            // 如果性别未选择，则弹出错误提示框
            JOptionPane.showMessageDialog(this, "Please select your gender.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!termsCheckbox.isSelected()) {
            // 如果未同意协议，则弹出错误提示框
            JOptionPane.showMessageDialog(this, "Please accept the terms and conditions.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true; // 输入验证通过
    }

    private void showConfirmationMessage() { // 显示确认信息
        String gender = maleRadioButton.isSelected() ? "Male" : "Female"; // 获取性别
        String message = "User Information:\n\n" // 生成确认信息文本
                + "Name: " + nameField.getText() + "\n"
                + "Email: " + emailField.getText() + "\n"
                + "Gender: " + gender + "\n"
                + "Address: " + addressArea.getText();

        JOptionPane.showMessageDialog(this, message, "Registration Confirmation", JOptionPane.INFORMATION_MESSAGE); // 弹出确认信息提示框
        clearInputs(); // 清空输入框
    }

    private void clearInputs() { // 清空输入框
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        addressArea.setText("");
        termsCheckbox.setSelected(false);
    }

    public static void main(String[] args) { // 主方法
        System.setProperty("sun.java2d.noddraw", "true"); // 禁用硬件加速，防止显示异常

        SwingUtilities.invokeLater(Question_3::new); // 在事件分派线程中创建窗口
    }
}