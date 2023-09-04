package Lab1;

import java.util.Scanner;

public class Question_9 {
    public static double[] Sort(double[] a){
        for(int i=1;i<a.length;i++){
            double value=a[i];
            int position=i;
            while (position>0&&a[position-1]<value){
                a[position]=a[position-1];
                position--;
            }
            a[position]=value;
        }
        return a;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("please input the length of the array:");
        int n=sc.nextInt();
        double[] arr=new double[n];
        for(int i=0;i<n;i++){
            System.out.print("array["+(i+1)+"]=");
            arr[i]=sc.nextInt();
        }
        System.out.println("最大值"+(int)Sort(arr)[0]);
        System.out.println("次大值"+(int)Sort(arr)[1]);
    }
}
