package week2.exe2;

public class WoodenStructureAdapter implements Animal{
    WoodenHorse woodenHorse;

    public WoodenStructureAdapter(WoodenHorse woodenHorse){
        this.woodenHorse = woodenHorse;
    }


    @Override
    public void move() {
        woodenHorse.roll();
    }

    @Override
    public void mate(Animal partner) {
        woodenHorse.replicate();
    }

    @Override
    public String getInstanceOf() {
        return "WoodenHorse";
    }

    @Override
    public Genders getGender() {
        return null;
    }
}
