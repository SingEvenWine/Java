package Commonly_Used_Classes.Question1;

import static java.lang.Math.PI;

public class Circle {
    private double radius;
    public Circle(double radius){
        setRadius(radius);
    }

    public double ComputerArea(){
        return PI*getRadius()*getRadius();
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
