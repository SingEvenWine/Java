package IO_Honework;

import java.io.File;

public class Homework_2 {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Lenovo\\Desktop\\211210400209");
        visitAllDirsAndFiles(file,"211210400209");
    }

    public static void visitAllDirsAndFiles(File file,String keyword) {
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (subFiles != null) {
                for (File subFile : subFiles) {
                    visitAllDirsAndFiles(subFile,keyword);
                }
            }
        } else {
            if(file.getName().matches(".*"+keyword+".*")){
                long fileSize = file.length();
                String fileSizeStr;
                if (fileSize < 1024) {
                    fileSizeStr = fileSize + "B";
                } else if (fileSize < 1024 * 1024) {
                    fileSizeStr = fileSize / 1024 + "KB";
                } else {
                    fileSizeStr = fileSize / 1024 / 1024 + "MB";
                }
                System.out.println(file.getName()+" "+fileSizeStr);
            }
        }
    }
}
