package IO_Honework;

import java.io.File;

public class Homework_1 {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Lenovo\\Desktop\\211210400209\\论文");
        visitAllDirsAndFiles(file, 0);
    }

    public static void visitAllDirsAndFiles(File file, int level) {
        StringBuilder prefix = new StringBuilder();
        prefix.append("    ".repeat(Math.max(0, level)));
        if (file.isDirectory()) {
            System.out.println(prefix + file.getName() + "/");
            File[] subFiles = file.listFiles();
            if (subFiles != null) {
                for (File subFile : subFiles) {
                    visitAllDirsAndFiles(subFile, level + 1);
                }
            }
        } else {
            long fileSize = file.length();
            String fileSizeStr;
            if (fileSize < 1024) {
                fileSizeStr = fileSize + "B";
            } else if (fileSize < 1024 * 1024) {
                fileSizeStr = fileSize / 1024 + "KB";
            } else {
                fileSizeStr = fileSize / 1024 / 1024 + "MB";
            }
            System.out.println(prefix + file.getName() + " " + fileSizeStr);
        }
    }
}
