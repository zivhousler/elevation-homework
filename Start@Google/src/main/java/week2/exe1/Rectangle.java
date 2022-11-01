package week2.exe1;

public class Rectangle implements Shape{

    private double length, width;

    public Rectangle(double length, double width){
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return width*length;
    }
}
