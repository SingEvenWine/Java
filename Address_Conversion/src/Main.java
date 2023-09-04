import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请选择：");
            System.out.println("1.页式存储管理");
            System.out.println("2.段式存储管理");
            System.out.println("3.段页式存储管理");
            System.out.println("4.退出");
            String input = scanner.next();
            if (input.equals("1")) {
                Page.Main();
            } else if (input.equals("2")) {
                Segment.Main();
            } else if (input.equals("3")) {
                SegPage.Main();
            } else if (input.equals("4")) {
                System.exit(0);
            } else {
                System.out.println("输入错误，请重新输入！");
            }
        }
    }
}