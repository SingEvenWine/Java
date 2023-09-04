package Lab1;

import java.util.Scanner;

public class Question_2 {
    public static void main(String[] args){
        System.out.print("请输入一个三位数：");
        Scanner sc= new Scanner(System.in);
        int n=sc.nextInt();
        int a=n%10;
        int b=n/10%10;
        int c=n/100;
        int sum=a*a*a+b*b*b+c*c*c;
        if(sum!=n){
            System.out.println(n+"不是水仙花数");
        }
        else{
            System.out.println(n+"是水仙花数");
        }
    }
}
