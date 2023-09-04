package Lab1;

public class Question_8 {
    public static void main(String[] args) {
        int a1=1,a2=1,a3;
        System.out.print(a1+" "+a2+" ");
        for(int i=0;i<38;i++){
            a3=a1+a2;
            a1=a2;
            a2=a3;
            System.out.print(a3+" ");
        }
    }
}
