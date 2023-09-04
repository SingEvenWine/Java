package Lab2.Question3;

import static java.lang.Math.PI;

public class Circle implements Shape{
    private double r;
    public Circle(double r){
        setR(r);
    }

    public double area(){
        return PI*getR()*getR();
    }
    public double perimeter(){
        return 2*PI*getR();
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getR() {
        return r;
    }
}
