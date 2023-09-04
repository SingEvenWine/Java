package Commonly_Used_Classes.Question2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lottery {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请在1-49中选择六位数字");
        int[] useNum=new int[6];
        for(int i=0;i< useNum.length;i++){
            System.out.println("请输入第"+(i+1)+"个数字");
            useNum[i]=sc.nextInt();
            if(useNum[i]<1||useNum[i]>49){
                System.out.println("输入错误，请输入1-49之间（包含1和49）的数字");
                i--;
            } else if (containsUser(useNum,i)) {//遍历i之前的所有数，若发现与useNum[i]相等的数则返回ture，否则返回false
                System.out.println("输入数字重复，请重新输入");
                i--;
            }
        }
        Arrays.sort(useNum);
        System.out.println("您选择的数字为："+Arrays.toString(useNum));

        int[] lotNum=getLotNum();//获取获奖号码组
        Arrays.sort(lotNum);
        System.out.println("本次中奖号码为："+ Arrays.toString(lotNum));
        int num=matchNum(useNum,lotNum);
        if(num==6){
            System.out.println("恭喜您，您中了一等奖！");
        } else if (num==5) {
            System.out.println("恭喜您，您中了二等奖！");
        }else if (num==4) {
            System.out.println("恭喜您，您中了三等奖！");
        }else {
            System.out.println("很遗憾，本次您并未中奖。");
        }
    }

    public static boolean contains(int[] arr,int val,int end){
        //判断数组中是否包含某一元素
        for(int i=0;i<end;i++){
            if(arr[i]==val){
                return true;//如果该数组包含该值返回ture
            }
        }
        return false;//否则返回false
    }
    public static boolean containsUser(int[] arr,int end){
        //判断数组中是否包含重复元素
        for(int i=0;i<end;i++){
            if(arr[i]==arr[end]){
                return true;
            }
        }
        return false;
    }
    public static int matchNum(int[] arr1, int[] arr2) {
        //验证中奖
        int count=0;
        for (int j : arr1) {//遍历arr2，若存在arr1中的元素则count加1，最终返回count
            if (contains(arr2, j, arr2.length)) {
                count++;
            }
        }
        return count;
    }

    public static int[] getLotNum() {
        //生成获奖号码
        int[] lotNum=new int[6];
        Random random=new Random();
        for(int i=0;i<lotNum.length;i++){
            int num;
            do {
                num=random.nextInt(49)+1;
            }while (contains(lotNum,num,i));//判断是否重复，若无重复contains返回false num赋值给lotNum
            lotNum[i]=num;//若存在重复则继续执行random直至没有重复出现。
        }
        return lotNum;
    }
}
