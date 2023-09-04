package Campaign_Management_System.Form;

import javax.swing.*;
import java.awt.*;

public class JudgeFrame extends JFrame {
    //private ParticipantServicelmpl participants=new ParticipantServicelmpl(); // 参赛者数组
    private JLabel titleLabel;
    private JButton startButton;
    private JButton stopButton;
    private JTextArea logTextArea;

    public JudgeFrame() {
        // 设置窗口标题
        setTitle("裁判界面");

        // 设置窗口大小
        setSize(600, 400);

        // 设置窗口关闭时的默认操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建面板
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 创建标题标签
        titleLabel = new JLabel("比赛裁判系统");
        titleLabel.setFont(new Font("宋体", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("开始比赛");
        stopButton = new JButton("停止比赛");
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // 创建日志文本框
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 将面板添加到窗口中
        add(panel);

        // 显示窗口
        setVisible(true);
    }
    public void scoreParticipant(String participantName, int score) {
    // 在日志文本框中记录评分信息
    logTextArea.append(participantName + " 得分：" + score + "\n");

    // 在参赛者数组中查找参赛者
    //Participant participant = participants.getParticipantByName(participantName);
    //为参赛者增加分数
    //participant.setScore(participant.getScore() + score);
}
    public static void main(String[] args) {
        new JudgeFrame();
    }
}