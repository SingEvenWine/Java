package Lab1;

import java.util.Scanner;

import static java.lang.Math.pow;

public class Question_4 {
    public static void main(String[] args) {
        System.out.print("请输入要求的精度值：");
        Scanner sc= new Scanner(System.in);
        double n=sc.nextDouble();
        double p=0;
        int i=0;
        for (i=0;1.0/(2*i+1)>n;i++){
            p=p+pow(-1.0,i)*1/(2.0*i+1.0);
        }
        p=p+pow(-1.0,i)*1/(2.0*i+1.0);
        System.out.println(p*4);
    }
}

