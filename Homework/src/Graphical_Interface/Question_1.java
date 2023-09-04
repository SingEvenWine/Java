package Graphical_Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Question_1 extends JFrame implements ActionListener {
    private JTextField textField1;
    private JTextField textField2;
    private JButton button;

    public Question_1() {
        super("Swap TextFields");

        // 创建两个文本框，并设置字体
        Font font = new Font("微软雅黑", Font.PLAIN, 14);
        textField1 = new JTextField(20);
        textField1.setFont(font);
        textField2 = new JTextField(20);
        textField2.setFont(font);

        // 创建一个按钮
        button = new JButton("Swap");
        button.setFocusable(false);
        button.addActionListener(this);

        // 创建一个面板，并将组件添加到面板中
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(textField1);
        panel.add(textField2);
        panel.add(button);

        // 将面板添加到窗口中
        setContentPane(panel);

        // 设置窗口大小和关闭行为
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // 获取文本框的文本内容
        String text1 = textField1.getText();
        String text2 = textField2.getText();

        // 交换两个文本框的文本内容
        textField1.setText(text2);
        textField2.setText(text1);
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.noddraw", "true");
        new Question_1();
    }
}
