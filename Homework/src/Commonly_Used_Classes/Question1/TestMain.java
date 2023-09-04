package Commonly_Used_Classes.Question1;

public class TestMain {
    public static void main(String[] args) {
        Circle circle=new Circle(2);
        Cone cone=new Cone(circle.ComputerArea(), 2);
        System.out.println(cone.computerVolume());
    }
}
