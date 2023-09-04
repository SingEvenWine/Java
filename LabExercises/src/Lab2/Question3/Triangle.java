package Lab2.Question3;

import static java.lang.Math.sqrt;

public class Triangle implements Shape{
    private double a,b,c;

    public Triangle(double a,double b,double c){
        setA(a);
        setB(b);
        setC(c);
    }

    @Override
    public double area() {
        return sqrt(perimeter()/2*(perimeter()/2-getA())*(perimeter()/2-getB())*(perimeter()/2-getC()));
    }

    @Override
    public double perimeter() {
        return getA()+getB()+getC();
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}
