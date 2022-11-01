package week2.exe2;

import utils.RandomData;

public class Slug extends AbstractAnimal {

    public Slug() {
        super(RandomData.generateRandomNumber(0, 1));
    }

    public Genders getGender() {
        return this.gender;
    }

    @Override
    public String getInstanceOf() {
        return "Slug";
    }

    @Override
    public void mate(Animal partner) {
        super.mate(partner);
    }

    @Override
    public void move() {
        System.out.println("This " + getInstanceOf() + " (ID:" + this.id +")" + " is moving from point A to point B");
    }

    @Override
    public String toString() {
        return "Slug:" + super.toString();
    }
}
