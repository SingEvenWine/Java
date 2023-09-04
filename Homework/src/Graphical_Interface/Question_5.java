package Graphical_Interface;

import javax.swing.*;
import java.awt.*;

public class Question_5 extends JFrame{
    private final JTextArea textArea;
    public Question_5(){
        // 创建一个名为“Font Dialog Example”的JFrame实例，用作主窗口
        super("Font Dialog Example");
        // 设置窗口关闭行为为“EXIT_ON_CLOSE”，即点击窗口关闭按钮时退出程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口的初始大小
        setSize(400, 600);
        // 设置窗口位置（在屏幕中央）
        setLocationRelativeTo(null);

        // 设置窗口的布局管理器为 BorderLayout
        setLayout(new BorderLayout());

        // 创建一个JTextArea实例，设置文本区域的行数和列数
        textArea = new JTextArea(10, 30);
        // 创建一个JScrollPane实例，并将文本区域作为参数传递，以便为文本区域添加滚动条
        JScrollPane scrollPane = new JScrollPane(textArea);
        // 将带有滚动条的文本区域（即JScrollPane实例）添加到窗口的中心位置
        add(scrollPane, BorderLayout.CENTER);

        // 创建一个名为“Choose Font”的JButton实例
        JButton fontButton = new JButton("Choose Font");
        // 为“Choose Font”按钮添加一个ActionListener，以便在单击按钮时执行代码
        fontButton.addActionListener(e -> {
            // 创建FontDialog实例时将主窗口和文本区域作为参数传递
            FontDialog fontDialog = new FontDialog(this, textArea);
            // 设置字体对话框为可见
            fontDialog.setVisible(true);
        });

        // 将“Choose Font”按钮添加到窗口的北部位置
        add(fontButton, BorderLayout.NORTH);
        // 设置主窗口为可见
        setVisible(true);
    }
    public static void main(String[] args) {
        // 设置系统属性，解决部分系统下图形界面渲染问题
        System.setProperty("sun.java2d.noddraw", "true");
        new Question_5();
    }
}

class FontDialog extends JDialog {
    // 声明组件
    private final JComboBox<String> fontFamilyComboBox;
    private final JComboBox<Integer> fontSizeComboBox;
    private final JCheckBox boldCheckBox;
    private final JCheckBox italicCheckBox;
    private final JTextArea textArea;

    public FontDialog(JFrame parent, JTextArea textArea) {
        // 调用父类构造方法，创建模态对话框
        super(parent, "Font", true);
        // 设置对话框中文本区域的引用
        this.textArea = textArea;
        // 设置对话框大小
        setSize(300, 200);
        // 设置对话框位置
        setLocationRelativeTo(null);
        // 设置对话框布局
        setLayout(new GridBagLayout());
        // 创建布局约束对象
        GridBagConstraints c = new GridBagConstraints();
        // 设置约束，使组件水平填充单元格
        c.fill = GridBagConstraints.HORIZONTAL;

        // 创建并添加“字体”标签
        JLabel fontFamilyLabel = new JLabel("Font:");
        c.gridx = 0;
        c.gridy = 0;
        add(fontFamilyLabel, c);

        // 获取系统可用字体
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        // 创建字体下拉列表
        fontFamilyComboBox = new JComboBox<>(fontNames);
        c.gridx = 1;
        c.gridy = 0;
        add(fontFamilyComboBox, c);

        // 创建并添加“字号”标签
        JLabel fontSizeLabel = new JLabel("Size:");
        c.gridx = 0;
        c.gridy = 1;
        add(fontSizeLabel, c);

        // 创建字号下拉列表
        Integer[] fontSizes = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};
        fontSizeComboBox = new JComboBox<>(fontSizes);
        c.gridx = 1;
        c.gridy = 1;
        add(fontSizeComboBox, c);

        // 创建并添加“粗体”复选框
        boldCheckBox = new JCheckBox("Bold");
        c.gridx = 0;
        c.gridy = 2;
        add(boldCheckBox, c);

        // 创建并添加“斜体”复选框
        italicCheckBox = new JCheckBox("Italic");
        c.gridx = 1;
        c.gridy = 2;
        add(italicCheckBox, c);

        // 创建并添加“确定”按钮
        JButton okButton = new JButton("OK");
        // 为“确定”按钮添加点击事件监听器
        okButton.addActionListener(e -> {
            // 应用选择的字体
            applyFont();
            // 关闭对话框
            dispose();
        });
        c.gridx = 0;
        c.gridy = 3;
        add(okButton, c);

        // 创建并添加“取消”按钮
        JButton cancelButton = new JButton("Cancel");
        // 为“取消”按钮添加点击事件监听器
        cancelButton.addActionListener(e -> dispose());
        c.gridx = 1;
        c.gridy = 3;
        add(cancelButton, c);
    }

    // 应用选择的字体
    private void applyFont() {
        // 获取选择的字体名称
        String fontFamily = (String) fontFamilyComboBox.getSelectedItem();
        // 获取选择的字号
        int fontSize = (Integer) fontSizeComboBox.getSelectedItem();
        // 设置初始字体样式为普通
        int fontStyle = Font.PLAIN;
        // 如果选中“粗体”复选框，则将字体样式设置为粗体
        if (boldCheckBox.isSelected()) {
            fontStyle |= Font.BOLD;
        }
        // 如果选中“斜体”复选框，则将字体样式设置为斜体
        if (italicCheckBox.isSelected()) {
            fontStyle |= Font.ITALIC;
        }

        // 根据选择的字体名称、样式和字号创建字体对象
        Font newFont = new Font(fontFamily, fontStyle, fontSize);
        // 将新字体应用到文本区域
        textArea.setFont(newFont);
    }
}
