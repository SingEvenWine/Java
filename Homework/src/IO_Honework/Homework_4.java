package IO_Honework;

import java.io.*;
import java.util.Scanner;

public class Homework_4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入学生信息（学号 姓名 年龄）：");
        String id, name, age;

        while (true) {
            id = scanner.next();
            name = scanner.next();
            age = scanner.next();

            if (validateInput(id, name, age)) {
                break;
            } else {
                System.out.println("输入错误，请重新输入学生信息（学号 姓名 年龄）：");
            }
        }

        Student student = new Student(name, id, age);
        String fileName = "E:\\Project\\Java\\Homework\\src\\IO_Honework\\student.txt";
        saveStudentToFile(student, fileName);
        Student readStudent = readStudentFromFile(fileName);

        if (readStudent != null) {
            System.out.println("从文件中读取的学生信息：");
            System.out.println("学号：" + readStudent.getId());
            System.out.println("姓名：" + readStudent.getName());
            System.out.println("年龄：" + readStudent.getAge());
        } else {
            System.out.println("读取文件失败。");
        }
    }

    private static boolean validateInput(String id, String name, String age) {
        // 验证逻辑，例如对学号、姓名和年龄的格式和范围进行检查
        return !id.isEmpty() && !name.isEmpty() && !age.isEmpty();
    }

    private static void saveStudentToFile(Student student, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(student);
        } catch (IOException e) {
            System.err.println("保存学生信息到文件失败：" + e.getMessage());
        }
    }

    private static Student readStudentFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Student) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("从文件读取学生信息失败：" + e.getMessage());
            return null;
        }
    }
}


class Student implements Serializable{
    private String name;
    private String id;
    private String age;

    public Student(String name, String id, String age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}