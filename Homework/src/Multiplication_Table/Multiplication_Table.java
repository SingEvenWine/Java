package Multiplication_Table;

public class Multiplication_Table {
    public static void main(String[] args) {
        for(int i=1;i<=9;i++){
            for(int j=i;j<=9;j++){
                if(i*j%28==0){
                    System.out.println(i*j);
                }
                if(i*j%12==0){
                }
                if(i*j%28!=0&&i*j%12!=0){
                    System.out.print(i*j+" ");
                }
            }
        }
    }
}
