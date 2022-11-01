package week3.BehavorialPatterns;

import utils.RandomData;

public class Group implements Appliance {
    private final int id;
    private int size;

    public Group() {
        this.id = Integer.parseInt(RandomData.getRandomId());
        this.size = RandomData.generateRandomNumber(1, 10);
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", size=" + size +
                '}';
    }
}
