package week3.BehavorialPatterns;

import utils.RandomData;

public class User implements Appliance {

    private final int id;
    private final String name;
    private final String password;

    public User() {
        this.id = Integer.parseInt(RandomData.getRandomId());
        this.name = RandomData.generateRandomName();
        this.password = "123456789";
    }

    @Override
    public void visit(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
