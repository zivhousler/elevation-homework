package week2.exe2;


import utils.RandomData;

public abstract class AbstractAnimal implements Animal {
    protected final Genders gender;
    protected final String id;
    protected float weight;

    public AbstractAnimal(float weight) {
        this.gender = Genders.values()[RandomData.generateRandomNumber(0, Genders.values().length)];
        this.id = String.valueOf(RandomData.generateRandomNumber(100000, 999999));
        this.weight = weight;
    }

    public AbstractAnimal(Genders gender, String id, float weight) {
        this.gender = gender;
        this.id = id;
        this.weight = weight;
    }

    public void mate(Animal partner) {
        System.out.println("this: " + this.getInstanceOf());
        System.out.println("partner: " + partner.getInstanceOf());
        System.out.println("are they of the same breed? " + isSameBreed(this, partner));
        System.out.println("are they the same gender? " + isSameGender(this, partner));
        if (isSameBreed(this, partner) && !isSameGender(this, partner)) {
            System.out.println("BREEDABLE!");
        } else {
            System.out.println("none breedable");
        }
        System.out.println("----------------");
    }

    private boolean isSameGender(Animal animal1, Animal animal2) {
        return animal1.getGender() == animal2.getGender();
    }

    private boolean isSameBreed(Animal animal1, Animal animal2) {
        return animal1.getInstanceOf() == animal2.getInstanceOf();
    }

    @Override
    public String toString() {
        return "AbstractAnimal{" +
                "gender=" + gender +
                ", id='" + id + '\'' +
                ", weight=" + weight +
                "}\n";
    }
}

enum Genders {MALE, FEMALE};

