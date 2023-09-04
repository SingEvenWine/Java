package Campaign_Management_System.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegisterFrame extends JFrame {
    private JButton loginButton;
    private JButton registerButton;

    public LoginRegisterFrame() {
        // 设置窗口标题
        super("运动会管理系统 - 登录注册界面");

        // 设置窗口大小和布局
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建并添加标题标签
        JLabel titleLabel = new JLabel("运动会管理系统", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 设置按钮水平居中对齐，并添加间隔
        add(buttonPanel, BorderLayout.CENTER);

        // 创建登录按钮
        loginButton = new JButton("登录");
        loginButton.setPreferredSize(new Dimension(80, 30)); // 设置按钮尺寸
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里执行登录逻辑代码
                new LoginFrame();
                setVisible(false);
            }
        });
        buttonPanel.add(loginButton);

        // 创建注册按钮
        registerButton = new JButton("注册");
        registerButton.setPreferredSize(new Dimension(80, 30)); // 设置按钮尺寸
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里执行注册逻辑代码
                RegisterFrame registerFrame = new RegisterFrame();
                setVisible(false);
            }
        });
        buttonPanel.add(registerButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginRegisterFrame frame = new LoginRegisterFrame();
                frame.setVisible(true);
            }
        });
    }
}
