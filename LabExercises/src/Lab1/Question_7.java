package Lab1;

public class Question_7 {
    public static boolean Func(int a) {
        for (int j = 2; j < a; j++) {
            if (a % j == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 100; i <= 200; i++) {
            if(Func(i)){
                System.out.print(i+" ");
            };
        }
    }
}