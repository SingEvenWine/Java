package Lab2.Question3;

import java.util.Scanner;

public class TestShape {
     static void testshape(Scanner sc) {
        System.out.println("圆测试");
        System.out.println("请输入圆的半径: ");
        double r=sc.nextDouble();
        Circle circle=new Circle(r);
        System.out.println("圆的面积: "+circle.area());
        System.out.println("圆的周长: "+circle.perimeter());
        System.out.println("矩形测试");
        System.out.println("请输入矩形的长度和宽度: ");
        double x=sc.nextDouble();
        double y=sc.nextDouble();
        Rectangle rectangle=new Rectangle(x,y);
        System.out.println("矩形的面积: "+rectangle.area());
        System.out.println("矩形的周长: "+rectangle.perimeter());
        System.out.println("三角形测试");
        System.out.println("请输入三角形的三边: ");
        double a=sc.nextDouble();
        double b=sc.nextDouble();
        double c=sc.nextDouble();
        Triangle triangle=new Triangle(a,b,c);
        System.out.println("三角形的面积: "+triangle.area());
        System.out.println("三角形的周长: "+triangle.perimeter());
    }
    public static void main(String[] args) {
        //testshape(null);
        /*
         * 这是专用一个测试程序，用于对学生所写程序的进行测试
         * 第一个输入的数为测试用例的id号，
         * 余下的输入则为标准输入。
         * 测试1 用户编写的testshape()的运行结果
         * 测试2 circle对象的构造和area()与perimeter()方法
         * 测试3 rectangle对象的构造和area()与perimeter()方法
         * 测试4 triangle对象的构造和area()与perimeter()方法
         */
        Scanner sc = new Scanner(System.in);
        int func = sc.nextInt();
        switch (func) {
            case 1:
                testshape(sc);
                break;
            case 2:
                circleTest();
                break;
            case 3:
                rectangleTest();
                break;
            case 4:
                triangleTest();
                break;

            default:
                break;
        }
    }


    private static void circleTest() {
        //三组测试参数，构造多个圆，验证面积与周长的计算结果
        double[] rList = {1.0,12.35,7.56,45.23,6.0};
        for (int i=0;i<rList.length;i++) {
            Shape s = new Circle(rList[i]);
            test(s.area()==Math.PI*rList[i]*rList[i],"Pass Circle Test","Fail Circle Test");
            test(s.perimeter()==2*Math.PI*rList[i],"Pass Circle Test","Fail Circle Test");
        }

    }

    private static void rectangleTest() {
        //三组测试参数，构造多个矩形，验证面积与周长的计算结果
        double[][] pList = {{1.0,12.35},{7.56,45.23}};
        for (int i=0;i<pList.length;i++) {
            double a=pList[i][0];
            double b=pList[i][1];
            Shape s = new Rectangle(a,b);
            test(s.area()==a*b,"Pass Rectangle Test","Fail Rectangle Test");
            test(s.perimeter()==(a+b)*2,"Pass Rectangle Test","Fail Rectangle Test");
        }

    }

    private static void triangleTest() {
        //三组测试参数，构造多个三角形，验证面积与周长的计算结果
        double[][] pList = {{3.0,4.0,5.6},{45.0,56.0,78.0}};
        for (int i=0;i<pList.length;i++) {
            double a=pList[i][0];
            double b=pList[i][1];
            double c=pList[i][2];
            Shape s = new Triangle(a,b,c);
            double p =  (a + b + c) / 2;
            double area1 = Math.sqrt(p * (p - a) * (p - b) * (p - c));
            test(Math.abs(s.area()-area1)<1.0e-9,"Pass Triangle Test","Fail Triangle Test");
            test(s.perimeter()==(a+b+c),"Pass Triangle Test","Fail Triangle Test");
        }

    }

    private static void test(boolean pass, String corr, String wrong) {
        if  (pass) {
            System.out.println(corr);
        } else {
            System.out.println(wrong);
        }

    }
}
