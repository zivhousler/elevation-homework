package week2.exe1;

public class Square implements Shape{

    private double length, width;

    public Square(double length, double width){
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return width*length;
    }
}
