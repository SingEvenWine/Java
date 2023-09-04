package De_Magic;

import java.util.Scanner;

public class De_Magic {
    public static void main(String[] args) {
        int a,b;
        Scanner scanner=new Scanner(System.in);
        System.out.print("请输入被除数m和除数n：");
        a=scanner.nextInt();
        b=scanner.nextInt();
        if(b!=0){
            System.out.println("商为"+a/b);
        }else {
            System.out.print("除数不能为0");
        }
    }
}
