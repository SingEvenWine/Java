package Lab2.Question2;

import java.util.Scanner;

public class PersonApp {
    public static void appMain(){
        Person person=new Person("张淼",21,'男');
        Student student=new Student("高坤",22,'男',5001212);
        Teacher teacher=new Teacher("李芳香",35,'女',"程序设计基础");
        System.out.println("Person: "+person.show());
        System.out.println("Student: "+student.show());
        System.out.println("Teacher: "+teacher.show());
    }
    public static void main(String[] args) {
        /*
         * 这是一个自测试程序，用于对学生所写程序的进行测试
         * 程序运行总先输入【1|2|3|4】，为测试项的id号，其它输入无任何作用。
         * 测试id=1 用户编写的appMain的运行结果
         * 测试id=2 Person对象的构造和show() 方法
         * 测试id=3 Student对象的构造和show()方法
         * 测试id=4 Teacher对象的构造和show()方法
         */
        Scanner sc = new Scanner(System.in);
        int func = sc.nextInt();
        switch (func) {
            case 1:
                appMain();
                break;
            case 2:
                personTest();
                break;
            case 3:
                studentTest();
                break;
            case 4:
                teacherTest();
                break;

            default:
                break;
        }

    }

    private static void teacherTest() {


        Teacher t = new Teacher("李芳香", 35, '女', "程序设计基础");
        test(t.getName().equals("李芳香") && t.getAge()==35
                        && t.getSex()=='女' && t.getCourse().equals("程序设计基础"),
                "Teacher构造函数正确","Teacher构造函数不正确");
        test(t.show().equals("李芳香 女 35 程序设计基础"),"teacher.show()通过","teacher.show()未通过");
    }

    private static void studentTest() {
        Student s = new Student("高坤", 22, '男', 5001212);
        test(s.getName().equals("高坤") && s.getAge()==22
                        && s.getSex()=='男' && s.getId()==5001212,
                "Student构造函数正确","Student构造函数不正确");
        test(s.show().equals("高坤 男 22 5001212"),"student.show()通过","student.show()未通过");
    }

    private static void personTest() {

        Person p = new Person("张淼", 21, '男');
        test(p.getName().equals("张淼")  && p.getAge()==21
                && p.getSex()=='男', "Person构造函数正确","Person构造函数不正确");
        test(p.show().equals("张淼 男 21"),"person.show()通过","person.show()未通过");


    }

    private static void test(boolean pass, String corr, String wrong) {
        if  (pass) {
            System.out.println(corr);
        } else {
            System.out.println(wrong);
        }

    }
}
