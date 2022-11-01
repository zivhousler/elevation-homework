package week2.exe2;

public class Farmer {
    private final String name;
    private final Farm farm;

    public Farmer(String name) {
        this.name = name;
        this.farm = new Farm();
    }

    public Farm getFarm() {
        return farm;
    }

    public Animal request() {
        return farm.request();
    }

    public void move() {
        farm.provideAnimal().move();
    }
}
