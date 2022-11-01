package week2.LambdasStreams;

import utils.RandomData;

import java.time.LocalDate;
import java.util.Objects;

public class Item implements Comparable {

    private final String name;
    private final Types type;
    private final LocalDate expiryDate;
    private final double weight;

    public Item() {
        this.name = RandomData.generateRandomName();
        this.type = Types.values()[RandomData.generateRandomNumber(0,Types.values().length)];
        this.weight = RandomData.getRandomFloatNumber(1,1000);
        this.expiryDate = LocalDate.now().minusDays(10).plusDays(RandomData.generateRandomNumber(0,30));
    }

    protected String getName() {
        return name;
    }

    protected Types getType() {
        return type;
    }

    protected double getWeight() {
        return weight;
    }

    protected LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "\n\tItem{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", weight=" + weight +
                ", expiryDate=" + expiryDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.weight, weight) == 0 && Objects.equals(name, item.name) && type == item.type && Objects.equals(expiryDate, item.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, weight, expiryDate);
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Item)) {
            throw new IllegalArgumentException("This object is not a type of Item " + o);
        }
        if (this.expiryDate.isBefore(((Item) o).expiryDate)) return -1;
        if (this.expiryDate.isAfter(((Item) o).expiryDate)) return 1;
        return 0;
    }
}

enum Types {
    ROCK("Rock"), PAPER("Paper"), SCISSOR("Scissor");
    final String name;

    Types(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
