package Commonly_Used_Classes.Question1;

public class Cone {
    private double bottom,height;

    public Cone(double bottom,double height){
        setBottom(bottom);
        setHeight(height);
    }

    public double computerVolume(){
        return 1.0/3*bottom*height;
    }

    public void setBottom(double bottom) {
        this.bottom = bottom;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getBottom() {
        return bottom;
    }

    public double getHeight() {
        return height;
    }
}
