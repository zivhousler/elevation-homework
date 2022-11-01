package week2.exe2;

import utils.RandomData;

public class Kuala extends AbstractAnimal {

    public Kuala() {
        super(RandomData.generateRandomNumber(4000, 10000));
    }

    public Genders getGender() {
        return this.gender;
    }

    @Override
    public String getInstanceOf() {
        return "Kuala";
    }

    @Override
    public void move() {
        System.out.println("This " + getInstanceOf() + " (ID:" + this.id +")" + " is moving from point A to point B");
    }

    @Override
    public void mate(Animal partner) {
        super.mate(partner);
    }

    @Override
    public String toString() {
        return "Kuala:" + super.toString();
    }
}