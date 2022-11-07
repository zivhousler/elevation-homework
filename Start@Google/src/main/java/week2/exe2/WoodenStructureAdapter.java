package week2.exe2;

public class WoodenStructureAdapter implements Animal{
    WoodenStructures woodenStructure;

    public WoodenStructureAdapter(WoodenStructures woodenStructure){
        this.woodenStructure = woodenStructure;
    }

    @Override
    public void move() {
        woodenStructure.roll();
    }

    @Override
    public void mate(Animal partner) {
        woodenStructure.replicate();
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
