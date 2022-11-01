package week2.exe2;

public class FlyingDragon extends Dragon{

    public FlyingDragon(){
        super();
    }

    @Override
    public void move(){
        super.move();
        System.out.println("But when it is using it's wings to fly!");
    }
}
