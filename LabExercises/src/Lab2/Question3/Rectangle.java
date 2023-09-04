package Lab2.Question3;

public class Rectangle implements Shape{
    private double x,y;

    public Rectangle(double x,double y){
        setX(x);
        setY(y);
    }

    @Override
    public double area() {
        return getX()*getY();
    }

    @Override
    public double perimeter() {
        return 2*(getX()+getY());
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
