import java.util.Scanner;

public class Segment {
    // 设置段表
    private static int[][] segmentTable = {
            { 1 * 1024, 1 * 1024 },
            { 2 * 1024, 2 * 1024 },
            { 3 * 1024, 3 * 1024 },
            { 4 * 1024, 4 * 1024 },
            { 5 * 1024, 5 * 1024 }
    };

    public static int[][] getSegmentTable() {
        return segmentTable;
    }

    public static void setSegmenttable() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入段表大小：");
        int size = scanner.nextInt();
        int[][] segmentTable = new int[size][2];
        System.out.println("请输入段表内容：");
        for (int i = 0; i < size; i++) {
            // 判断是否重复
            boolean flag = true;
            while (flag) {
                flag = false;
                segmentTable[i][0] = scanner.nextInt();
                segmentTable[i][1] = scanner.nextInt();
                if (segmentTable[i][0] == 0 && segmentTable[i][1] == 0) {
                    flag = true;
                    System.out.println("段表内容不能为0,请重新输入:");
                }
                for (int j = 0; j < i; j++) {
                    if (segmentTable[i][0] == segmentTable[j][0] && segmentTable[i][1] == segmentTable[j][1]) {
                        flag = true;
                        System.out.println("段表内容不能重复,请重新输入:");
                    }
                }
            }
        }
        Segment.segmentTable = segmentTable;
        scanner.close();
    }

    // 随机生成段表
    public static void setSegmenttable(int size) {
        int[][] segmentTable = new int[size][2];
        int[] used = new int[8 * size + 1];
        int count = 0;

        while (count < size) {
            int start = (int) (Math.random() * 8 * size) + 1;
            if (used[start] == 0) {
                used[start] = 1;
                segmentTable[count][0] = start;
                count++;
            }
        }

        for (int i = 0; i < size; i++) {
            boolean flag = true;
            while (flag) {
                flag = false;
                int end = (int) (Math.random() * 2 * size) + 1;
                for (int j = 0; j < i; j++) {
                    if (end == segmentTable[j][1]) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    segmentTable[i][1] = end;
                }
            }
        }

        Segment.segmentTable = segmentTable;
    }

    public static void printSegmentTable() {
        System.out.println("段号\t段基址\t段长");
        for (int i = 0; i < segmentTable.length; i++) {
            System.out.println(i + "\t" + segmentTable[i][0] + "0k" + "\t" + segmentTable[i][1] + "k");
        }
    }

    public static int translate(int segmentNumber, int offset) {
        if (segmentNumber >= segmentTable.length) {
            System.out.println("段号为: " + segmentNumber + " > 段表长度: " + segmentTable.length + "发生越界中断");
            return -1;
        }
        if (offset >= segmentTable[segmentNumber][1] * 1024) {
            System.out.println("段长为: " + segmentTable[segmentNumber][1] + "k < " + offset + " 发生越界中断");
            return -1;
        }
        int physicalAddress = segmentTable[segmentNumber][0] * 10 * 1024 + offset;
        return physicalAddress;
    }

    public static void Main() {
        setSegmenttable(5);
        printSegmentTable();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入段号：");
        int segmentNumber = scanner.nextInt();
        System.out.print("请输入段内偏移量：");
        int offset = scanner.nextInt();
        int physicalAddress = translate(segmentNumber, offset);
        if (physicalAddress != -1) {
            System.out.println(
                    "\n段号为: " + segmentNumber + "\n段内偏移量为: " + offset + "\n段基址为: " + segmentTable[segmentNumber][0]
                            + "0k");
            System.out.println("\n物理地址为: " + segmentTable[segmentNumber][0] * 10 + " * 1024" + " + " + offset + " = "
                    + physicalAddress);
        }
        // scanner.close();

    }

}
