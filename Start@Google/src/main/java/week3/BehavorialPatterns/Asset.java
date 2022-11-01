package week3.BehavorialPatterns;

import utils.RandomData;

public class Asset implements Appliance {

    private final int serialNumber;
    private String owner;
    private double rating;

    public Asset() {
        this.serialNumber = Integer.parseInt(RandomData.getRandomId());
        this.owner = RandomData.generateRandomName();
        this.rating = RandomData.generateRandomNumber(0, 6);
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Asset{" +
                "serialNumber=" + serialNumber +
                ", owner='" + owner + '\'' +
                ", rating=" + rating +
                '}';
    }
}
