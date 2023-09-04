package Lab1;

import java.util.Scanner;

public class Question_5 {
    public static double Func(double a) {
        double x2=0,x1=a/2;
        while (x1-x2>1.0e-5){
            x2=1.0/2*(x1+a/x1);
            x1=x2;
            x2=1.0/2*(x1+a/x1);
        }
        return x2;
    }
    public static void main(String[] args) {
        System.out.print("请输入a之值：");
        Scanner sc=new Scanner(System.in);
        double a;
        a=sc.nextDouble();
        System.out.println(a+"的平方根为"+Func(a));
    }
}
