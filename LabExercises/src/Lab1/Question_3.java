package Lab1;

import java.util.Scanner;

public class Question_3 {
    public static void main(String[] args){
        System.out.print("请输入数组中元素个数：");
        Scanner sc= new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        int sum=0;
        for(int i=0;i<n;i++){
            System.out.print("请输入第"+(i+1)+"个元素：");
            arr[i]=sc.nextInt();
            sum+=arr[i];
        }
        System.out.println("数组的和为"+sum);
        System.out.println("数组的平均值为"+(double)sum/n);
    }
}
