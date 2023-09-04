package Form;

import javax.swing.*;

import Service.TeacherService;

import java.awt.*;

public class mainFrame {
    public mainFrame() {
        JFrame mainFrame = new JFrame("实验室管理系统");
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JLabel title = new JLabel("实验室管理系统");
        title.setFont(new Font("宋体", Font.BOLD, 20));
        title.setBounds(110, 30, 180, 30);
        mainPanel.add(title);

        JButton teacherLoginBtn = new JButton("教师登录");
        teacherLoginBtn.setBounds(50, 100, 100, 30);
        mainPanel.add(teacherLoginBtn);
        teacherLoginBtn.addActionListener(e -> {
            // 教师登录按钮事件处理
            loginTeacher();
            mainFrame.dispose();
        });

        JButton adminLoginBtn = new JButton("管理员登录");
        adminLoginBtn.setBounds(200, 100, 100, 30);
        mainPanel.add(adminLoginBtn);
        adminLoginBtn.addActionListener(e -> {
            // 管理员登录按钮事件处理
            loginAdmin();
            mainFrame.dispose();
        });

        JButton teacherRegBtn = new JButton("教师注册");
        teacherRegBtn.setBounds(50, 150, 100, 30);
        mainPanel.add(teacherRegBtn);
        teacherRegBtn.addActionListener(e -> {
            // 教师注册按钮事件处理
            registerTeacher();
        });

        JButton exitBtn = new JButton("退出程序");
        exitBtn.setBounds(200, 150, 100, 30);
        mainPanel.add(exitBtn);
        exitBtn.addActionListener(e -> {
            // 退出程序按钮事件处理
            System.exit(0);
        });

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }
    
    private void registerTeacher() {
        JFrame registerFrame = new JFrame("教师注册");
        registerFrame.setSize(400, 300);
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setLocationRelativeTo(null);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(null);
        
        JLabel nameLabel = new JLabel("姓名：");
        nameLabel.setBounds(50, 50, 80, 30);
        registerPanel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(120, 50, 200, 30);
        registerPanel.add(nameField);

        JLabel idLabel = new JLabel("职工号：");
        idLabel.setBounds(50, 100, 80, 30);
        registerPanel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(120, 100, 200, 30);
        registerPanel.add(idField);

        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setBounds(50, 150, 80, 30);
        registerPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(120, 150, 200, 30);
        registerPanel.add(passwordField);

        JButton registerBtn = new JButton("注册");
        registerBtn.setBounds(50, 200, 100, 30);
        registerPanel.add(registerBtn);
        registerBtn.addActionListener(e->{
            //注册按钮事件处理
            String name = nameField.getText();
            String id = idField.getText();
            String password = new String(passwordField.getPassword());
            TeacherService teacherService = new TeacherService();
            if(teacherService.register(name, id, password)!=null){
                JOptionPane.showMessageDialog(null, "注册成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                registerFrame.dispose();
            }else{
                JOptionPane.showMessageDialog(null, "注册失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelBtn = new JButton("取消");
        cancelBtn.setBounds(200, 200, 100, 30);
        registerPanel.add(cancelBtn);
        cancelBtn.addActionListener(e->{
            //取消按钮事件处理
            registerFrame.dispose();
        });
        registerFrame.add(registerPanel);
        registerFrame.setVisible(true);
    }
    
    private void loginTeacher() {
        JFrame loginFrame = new JFrame("教师登录");
        loginFrame.setSize(400, 300);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);

        JLabel idLabel = new JLabel("职工号：");
        idLabel.setBounds(50, 50, 80, 30);
        loginPanel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(120, 50, 200, 30);
        loginPanel.add(idField);

        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setBounds(50, 100, 80, 30);
        loginPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(120, 100, 200, 30);
        loginPanel.add(passwordField);

        JButton loginBtn = new JButton("登录");
        loginBtn.setBounds(50, 150, 100, 30);
        loginPanel.add(loginBtn);
        loginBtn.addActionListener(e -> {
            // 登录按钮事件处理
            String id = idField.getText();
            String password = new String(passwordField.getPassword());
            TeacherService teacherService = new TeacherService();
            if (teacherService.login(id, password) != null) {
                loginFrame.dispose();
                new teacherMenu(teacherService.login(id, password));
            } else {
                JOptionPane.showMessageDialog(null, "登录失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelBtn = new JButton("取消");
        cancelBtn.setBounds(200, 150, 100, 30);
        loginPanel.add(cancelBtn);
        cancelBtn.addActionListener(e -> {
            // 取消按钮事件处理
            loginFrame.dispose();
        });

        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);
    }
    
    private void loginAdmin() {
        JFrame loginFrame = new JFrame("管理员登录");
        loginFrame.setSize(400, 300);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);

        JLabel idLabel = new JLabel("管理员帐号：");
        idLabel.setBounds(50, 50, 80, 30);
        loginPanel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(120, 50, 200, 30);
        loginPanel.add(idField);

        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setBounds(50, 100, 80, 30);
        loginPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(120, 100, 200, 30);
        loginPanel.add(passwordField);

        JButton loginBtn = new JButton("登录");
        loginBtn.setBounds(50, 150, 100, 30);
        loginPanel.add(loginBtn);
        loginBtn.addActionListener(e->{
            //登录按钮事件处理
            String id = idField.getText();
            String password = new String(passwordField.getPassword());
            if(id.equals("admin")&&password.equals("admin")){
                loginFrame.dispose();
                new adminMenu();
            }else{
                JOptionPane.showMessageDialog(null, "登录失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelBtn = new JButton("取消");
        cancelBtn.setBounds(200, 150, 100, 30);
        loginPanel.add(cancelBtn);
        cancelBtn.addActionListener(e->{
            //取消按钮事件处理
            loginFrame.dispose();
        });

        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);
    }
    public static void main(String[] args) {
        System.setProperty("sun.java2d.noddraw", "true");
        new mainFrame();
    }

}