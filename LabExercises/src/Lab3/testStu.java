package Lab3;

import java.util.Scanner;

public class testStu {
    public static void main(String[] args) {
        System.setProperty("java.util.logging.config.file", "E:\\Project\\Java\\LabExercises\\src\\Lab3\\hsqldb\\logging.properties");
        StudentDatabase studentDatabase = new StudentDatabase();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n选择操作：");
            System.out.println("1. 添加学生");
            System.out.println("2. 显示所有学生");
            System.out.println("3. 根据学号查找学生");
            System.out.println("4. 根据姓名查找学生");
            System.out.println("5. 根据ID删除学生");
            System.out.println("6. 按指定字段排序并显示结果");
            System.out.println("0. 退出");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    // 添加学生
                    System.out.println("输入学生姓名：");
                    String name = scanner.next();
                    System.out.println("输入学生学号：");
                    String studentNumber = scanner.next();
                    studentDatabase.addStudent(new Student(name, studentNumber));
                    System.out.println("学生已添加");
                }
                case 2 ->
                    // 显示所有学生
                        studentDatabase.displayAllStudents();
                case 3 -> {
                    // 根据学号查找学生
                    System.out.println("输入学生学号：");
                    String numberToFind = scanner.next();
                    studentDatabase.findStudentByStudentNumber(numberToFind);
                }
                case 4 -> {
                    // 根据姓名查找学生
                    System.out.println("输入学生姓名：");
                    String nameToFind = scanner.next();
                    studentDatabase.findStudentByName(nameToFind);
                }
                case 5 -> {
                    // 根据ID删除学生
                    System.out.println("输入学生ID：");
                    String idToDelete = scanner.next();
                    studentDatabase.deleteStudentByStudentNumber(idToDelete);
                }
                case 6 ->
                    // 按指定字段排序并显示结果
                        studentDatabase.displayStudentsSortedBy();
                case 0 -> running = false;
                default -> System.out.println("无效的选项，请重新选择");
            }
        }

        scanner.close();
    }
}
