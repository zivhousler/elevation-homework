package week2.exe2;

public class Main {
    public static void main(String[] args){
        Farmer farmer = new Farmer("Josh");
        System.out.println("\n" + farmer.getFarm());

        System.out.println("\nRequested a random animal: " + farmer.request());

//        FlyingDragon dragon = new FlyingDragon();
//        dragon.move();
        farmer.move();
    }
}
