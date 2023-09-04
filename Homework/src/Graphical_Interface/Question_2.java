package Graphical_Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Question_2 extends JFrame implements ActionListener {

    private JButton button;
    private JTextArea textArea;
    private int num = 0;

    public Question_2() {
        super("事件1");

        //定义一个文本框
        textArea = new JTextArea("欢迎过来点我！！！");
        textArea.setEditable(false); // 设置 JTextArea 为不可编辑
        textArea.setFocusable(false); // 设置 JTextArea 为不可获取焦点，以便用户无法选中其内容
        //定义一个按钮
        button = new JButton("+");
        button.setFocusable(false);
        button.addActionListener(this);
        //定义一个面板，并将按钮添加到面板中
        JPanel Panel = new JPanel(new FlowLayout());
        Panel.add(textArea);
        Panel.add(button);
        // 将面板添加到窗口中
        setContentPane(Panel);
        //设置窗口属性
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 检查 ActionEvent 的来源，如果是鼠标事件，则执行增加数字的操作
        if (e.getSource() == button) {
            num++;
            textArea.setText("你点击了" + num + "次！");
        }
    }

    public static void main(String[] args) {
        new Question_2();
    }
}
