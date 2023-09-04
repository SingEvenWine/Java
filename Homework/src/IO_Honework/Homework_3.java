package IO_Honework;

import java.io.File;

import java.io.InputStream;

import java.io.OutputStream;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

public class Homework_3 {
    public static void main(String[] args) {
        File source = new File("C:\\Users\\Lenovo\\Desktop\\211210400209\\Test");
        File dest = new File("C:\\Users\\Lenovo\\Desktop\\211210400209\\Java作业\\test");
        copyFileOrDirectory(source, dest);
    }

    public static void copyFileOrDirectory(File source, File dest) {
        if (source.isDirectory()) {
            copyDirectory(source, dest);
        } else {
            copyFile(source, dest);
        }
    }

    public static void copyDirectory(File sourceDir, File destDir) {
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        File[] files = sourceDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    copyFile(file, new File(destDir, file.getName()));
                } else {
                    copyDirectory(file, new File(destDir, file.getName()));
                }
            }
        }
    }

    public static void copyFile(File sourceFile, File destFile) {
        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(destFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
