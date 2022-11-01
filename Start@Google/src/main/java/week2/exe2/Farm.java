package week2.exe2;

import utils.RandomData;

import java.util.ArrayList;

public class Farm {
    private final ArrayList<Animal> animals;

    protected Farm() {
        animals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            acquire();
        }

        for (Animal animal : animals) {
            animal.mate(rollRandomAnimal());
        }
    }

    protected void acquire() {
        animals.add(rollRandomAnimal());
    }

    protected Animal request() {
        int index = RandomData.generateRandomNumber(0, animals.size());
        Animal animal = animals.get(index);
        animals.remove(index);
        return animal;
    }

    protected Animal provideAnimal() {
        return animals.get(RandomData.generateRandomNumber(0, animals.size()));
    }

    private Animal rollRandomAnimal() {
        Animals animalType = Animals.values()[RandomData.generateRandomNumber(0, Animals.values().length)];
        switch (animalType) {
            case SLUG:
                return new Slug();
            case DRAGON:
                return new Dragon();
            case KAKOON:
                return new Kuala();
            default:
                System.out.println("Farm - rollRandomAnimal: Couldn't roll an animal from the list of animals");
                throw new IllegalArgumentException("Couldn't roll an animal from the list of animals");
        }
    }

    @Override
    public String toString() {
        return "Farm{" +
                "animals=\n" + animals +
                '}';
    }
}

enum Animals {SLUG, DRAGON, KAKOON}
