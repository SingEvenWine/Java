import java.util.Scanner;

public class Page {
    // 设置页大小
    private static int PAGE_SIZE = 256;
    // 设置页表
    private static int[] PAGE_TABLE = { 0, 1, 2, 3, 4 };

    private static Address address = new Page().new Address();

    public static void setPAGE_SIZE(int PAGE_SIZE) {
        Page.PAGE_SIZE = PAGE_SIZE;
    }

    public static void setPAGE_TABLE(int[] PAGE_TABLE) {
        Page.PAGE_TABLE = PAGE_TABLE;
    }

    public static int getPAGE_SIZE() {
        return PAGE_SIZE;
    }

    public static int[] getPAGE_TABLE() {
        return PAGE_TABLE;
    }

    public static int translateAddress(int virtualAddress) {
        // 计算页号和页内偏移量
        int pageNumber = virtualAddress / PAGE_SIZE;
        int offset = virtualAddress % PAGE_SIZE;
        // 查找页表
        if (pageNumber >= PAGE_TABLE.length) {
            return -1;
        } else {
            address.setVirtualAddress(virtualAddress);
            address.setPageNumber(pageNumber);
            address.setOffset(offset);
            address.setBlockNumber(PAGE_TABLE[pageNumber]);
            address.setPhysicalAddress(PAGE_TABLE[pageNumber] * PAGE_SIZE + offset);
            return address.getPhysicalAddress();
        }
    }

    public static void printPageTable() {
        System.out.println("页号\t\t块号");
        for (int i = 0; i < PAGE_TABLE.length; i++) {
            System.out.println(i + "\t\t" + PAGE_TABLE[i]);
        }
    }

    public static void setPAGE_TABLE() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入页表大小：");
        int size = scanner.nextInt();
        int[] pageTable = new int[size];
        System.out.println("请输入页表内容：");
        for (int i = 0; i < size; i++) {
            // 判断是否重复
            boolean flag = true;
            while (flag) {
                flag = false;
                pageTable[i] = scanner.nextInt();
                if (pageTable[i] == 0) {
                    flag = true;
                    System.out.println("页表内容不能为0,请重新输入:");
                    break;
                }
                for (int j = 0; j < i; j++) {
                    if (pageTable[i] == pageTable[j]) {
                        flag = true;
                        System.out.println("页表内容重复，请重新输入：");
                        break;
                    }
                }
            }
        }
        scanner.close();
        setPAGE_TABLE(pageTable);
    }

    // 随机生成页表
    public static void setPAGE_TABLE(int size) {
        int[] pageTable = new int[size];
        int max = size;
        // 随机生成最大块号
        int maxBlockNumber = 2 * size; // 设置一个足够大的数
        max = (int) (Math.random() * maxBlockNumber) + size - 1;
        for (int i = 0; i < size; i++) {
            // 判断是否重复
            boolean flag = true;
            while (flag) {
                flag = false;
                pageTable[i] = (int) (Math.random() * max) + 1;
                for (int j = 0; j < i; j++) {
                    if (pageTable[i] == pageTable[j]) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        setPAGE_TABLE(pageTable);
    }

    // 随机选择页面大小
    public static void setPAGE_SIZE() {
        int[] pageSize = { 128, 256, 512, 1024, 2048, 4096 };
        setPAGE_SIZE(pageSize[(int) (Math.random() * pageSize.length)]);
    }

    public static void printPage() {
        System.out.println("\n页大小：" + PAGE_SIZE);
        System.out.println("页表大小：" + PAGE_TABLE.length + "\n");
        System.out.println("页号\t块号");
        for (int i = 0; i < PAGE_TABLE.length; i++) {
            System.out.println(i + "\t" + PAGE_TABLE[i]);
        }
    }

    // 将十进制转化为十六进制
    public static String toHex(int num) {
        String hex = "";
        while (num != 0) {
            int temp = num % 16;
            if (temp < 10) {
                hex = temp + hex;
            } else {
                hex = (char) (temp - 10 + 'A') + hex;
            }
            num /= 16;
        }
        return hex;
    }

    // 将十进制转化为二进制
    public static String toBinary(int num) {
        String binary = "";
        while (num != 0) {
            binary = num % 2 + binary;
            num /= 2;
        }
        return binary;
    }

    public static void Main() {
        setPAGE_SIZE();
        setPAGE_TABLE(5);
        printPage();
        System.out.print("\n请输入虚拟地址:");
        Scanner scanner = new Scanner(System.in);
        int virtualAddress = scanner.nextInt();
        int physicalAddress = translateAddress(virtualAddress);
        if (physicalAddress == -1) {
            System.out.println("页号超出范围！");
        } else {
            System.out.println("\n页号为:" + address.getPageNumber());
            System.out.println("块号为：" + address.getBlockNumber());
            System.out.println("页内偏移量为：" + address.getOffset());
            System.out.println("物理地址为：" + address.getPhysicalAddress());
            System.out.println("十六进制虚拟地址为：" + toHex(address.getVirtualAddress()));
            System.out.println("二进制虚拟地址为：" + toBinary(address.getPhysicalAddress()) + "\n");
        }
        //scanner.close();

    }

    class Address {
        int virtualAddress;
        int physicalAddress;
        int pageNumber;
        int offset;
        int blockNumber;

        public int getVirtualAddress() {
            return virtualAddress;
        }

        public void setVirtualAddress(int virtualAddress) {
            this.virtualAddress = virtualAddress;
        }

        public int getPhysicalAddress() {
            return physicalAddress;
        }

        public void setPhysicalAddress(int physicalAddress) {
            this.physicalAddress = physicalAddress;
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getBlockNumber() {
            return blockNumber;
        }

        public void setBlockNumber(int blockNumber) {
            this.blockNumber = blockNumber;
        }
    }
    public static void main(String[] args) {
        Main();
    }
}
