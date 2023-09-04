import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SegPage {
    private static int pageSize = 256;
    // 页表组
    private static ArrayList<int[]> pageTables = new ArrayList<>();

    static {
        pageTables.add(new int[] { 0, 1, 2, 3, 4 });
        pageTables.add(new int[] { 0, 1, 2, 3, 4 });
        pageTables.add(new int[] { 0, 1, 2, 3, 4 });
        pageTables.add(new int[] { 0, 1, 2, 3, 4 });
        pageTables.add(new int[] { 0, 1, 2, 3, 4 });
    }

    private static int[][] segmentTable = {
            { pageTables.get(0).length, 0 },
            { pageTables.get(1).length, 1 },
            { pageTables.get(2).length, 2 },
            { pageTables.get(3).length, 3 },
            { pageTables.get(4).length, 4 }
    };

public static void setPageTables() {
    // 随机生成页表
    pageTables.clear();
    int ListSize = (int) (Math.random() * 5) + 1;

    for (int k = 0; k < ListSize; k++) {
        int size = (int) (Math.random() * 10) + 1;
        int[] pageTable = new int[size];
        int maxBlockNumber = 2 * size;
        for (int i = 0; i < size; i++) {
            boolean flag = true;
            while (flag) {
                flag = false;
                int page = (int) (Math.random() * (maxBlockNumber - 1)) + 1;
                for (int j = 0; j < i; j++) {
                    if (page == pageTable[j] || page == 0) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    pageTable[i] = page;
                }
            }
        }
        SegPage.pageTables.add(pageTable);
    }
}

// 随机生成段表
public static void setSegmentTable() {
    segmentTable = new int[pageTables.size()][2];
    ArrayList<Integer> indices = new ArrayList<>();
    for (int i = 0; i < pageTables.size(); i++) {
        indices.add(i);
    }
    Collections.shuffle(indices);
    for (int i = 0; i < pageTables.size(); i++) {
        int index = indices.get(i);
        segmentTable[i][0] = pageTables.get(index).length;
        segmentTable[i][1] = index;
    }
}

    public static void printPageTables() {
        for (int i = 0; i < pageTables.size(); i++) {
            System.out.print("页表" + i + ":");
            System.out.println("\n页号\t块号");
            for (int j = 0; j < pageTables.get(i).length; j++) {
                System.out.println(j + "\t" + pageTables.get(i)[j]);
            }
            System.out.println();
        }
    }

public static void printSegmentTable() {
    System.out.println("段表:");
    System.out.println("段号\t页数\t页表号");
    for (int i = 0; i < segmentTable.length; i++) {
        System.out.println(i + "\t" + segmentTable[i][0] + "\t" + segmentTable[i][1]);
    }
}

public static void printPageTableByPageNumber(int segNum) {
    int pageNumber = segmentTable[segNum][1];
    if (pageNumber >= pageTables.size()) {
        System.out.println("页表不存在");
        return;
    }
    System.out.println("页表" + segmentTable[segNum][1] + ":");
    System.out.println("页号\t块号");
    for (int i = 0; i < pageTables.get(pageNumber).length; i++) {
        System.out.println(i + "\t" + pageTables.get(pageNumber)[i]);
    }
    System.out.println();
}

    public static int translate(int segment, int page, int offset) {
        if (segment < 0 || segment >= segmentTable.length) {
            System.out.println("段号越界");
            return -1;
        }
        if (page < 0 || page >= segmentTable[segment][0]) {
            System.out.println("页号越界");
            return -1;
        } else {
            printPageTableByPageNumber(segment);
        }
        int physicalAddress = pageTables.get(segmentTable[segment][1])[page] * pageSize + offset;
        return physicalAddress;
    }

    // 随机选择页面大小
    public static void setPAGE_SIZE() {
        int[] pageSize = { 128, 256, 512, 1024, 2048, 4096 };
        SegPage.pageSize = pageSize[(int) (Math.random() * pageSize.length)];
    }

    public static void Main() {
        setPageTables();
        setSegmentTable();
        setPAGE_SIZE();
        printSegmentTable();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\n请输入段号:");
        int segment = scanner.nextInt();
        System.out.print("请输入页号(0 - " + (segmentTable[segment][0] - 1) + "):");
        int page = scanner.nextInt();
        System.out.print("请输入偏移量:");
        int offset = scanner.nextInt();
        System.out.println();

        int physicalAddress = translate(segment, page, offset);
        int blockNumber = pageTables.get(segmentTable[segment][1])[page];
        if (physicalAddress != -1) {
            System.out.println("段号：" + segment);
            System.out.println("页表号：" + segmentTable[segment][1]);
            System.out.println("页号：" + page);
            System.out.println("块号：" + blockNumber);
            System.out.println("页面大小：" + pageSize);
            System.out.println("偏移量：" + offset);
            System.out.println("物理地址：" + physicalAddress);
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Main();
    }

    // scanner.close();
}
