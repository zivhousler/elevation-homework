package week2.exe2;

import utils.RandomData;

public class Dragon extends AbstractAnimal {

    public Dragon() {
        super(RandomData.generateRandomNumber(500000, 900000));
    }

    public Genders getGender() {
        return this.gender;
    }

    @Override
    public String getInstanceOf() {
        return "Dragon";
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
        return "Dragon:" + super.toString();
    }
}