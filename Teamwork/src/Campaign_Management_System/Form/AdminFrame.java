package Campaign_Management_System.Form;

import Campaign_Management_System.Service.*;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {
    private UserServiceImpl userService = new UserServiceImpl();
    private SportsEventServicelmpl sportsEventService = new SportsEventServicelmpl();
    private ParticipantServicelmpl participantService = new ParticipantServicelmpl();
    private JudgeServicelmpl judgeService = new JudgeServicelmpl();
    private ScoreServicelmpl scoreService = new ScoreServicelmpl();

    //管理员界面
    public AdminFrame() {
        setTitle("管理员界面"); // 设置窗口标题
        setSize(500, 350); // 设置窗口大小
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置窗口关闭操作
        setLocationRelativeTo(null); // 设置窗口位置（在屏幕中央）
        setLayout(null); // 设置布局
        setVisible(true);
        //创建菜单栏
        JMenuBar menuBar = new JMenuBar();
        //创建菜单
        JMenu menu1 = new JMenu("用户管理");
        JMenu menu2 = new JMenu("比赛管理");
        //添加菜单到菜单栏
        menuBar.add(menu1);
        menuBar.add(menu2);
        //添加菜单栏到窗口
        setJMenuBar(menuBar);
        //添加菜单项到菜单
        JMenuItem menuItem1 = new JMenuItem("添加用户");
        JMenuItem menuItem2 = new JMenuItem("删除用户");
        JMenuItem menuItem3 = new JMenuItem("修改用户");
        JMenuItem menuItem4 = new JMenuItem("查看用户");
        JMenuItem menuItem5 = new JMenuItem("添加比赛");
        JMenuItem menuItem6 = new JMenuItem("删除比赛");
        JMenuItem menuItem7 = new JMenuItem("修改比赛");
        JMenuItem menuItem8 = new JMenuItem("查看比赛");
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu1.add(menuItem3);
        menu1.add(menuItem4);
        menu2.add(menuItem5);
        menu2.add(menuItem6);
        menu2.add(menuItem7);
        menu2.add(menuItem8);
        //添加监听器
        menuItem1.addActionListener(e -> {
            //添加用户
            //new AddUserFrame();
        });
    }
    public static void main(String[] args) {
        new AdminFrame();
    }
}