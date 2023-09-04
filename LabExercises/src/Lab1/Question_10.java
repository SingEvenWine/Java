package Lab1;

import java.util.Scanner;

public class Question_10 {
    public static boolean check(int n){
        int sum=0;
        for(int i=1;i<n;i++){
            if(n%i==0){
                sum=sum+i;
            }
        }
        return sum == n;
    }
    public static void main(String[] args) {
        System.out.println("请输入一个1000以内后一个数：");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        if(check(n)){
            System.out.println(n+"是一个完数");
        }else {
            System.out.println(n+"不是一个完数");
        }
    }
}
