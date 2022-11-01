package week1;

import utils.NameGenerator;
import utils.RandomData;

public class Player {
    // ---------- Properties ---------- //
    private String name;
    private String number;
    private int grade;
    private Roles role;

    private static enum Roles {GOAL_KEEPER, DEFENDER, MIDFIELDER, ATTACKER}

    // ---------- Setters ---------- //
    private void setRole(Roles role) {
        this.role = role;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setNumber(String number) {
        this.number = number;
    }

    private void setGrade(int grade) {
        this.grade = grade;
    }

    // ---------- Getters ---------- //
    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getGrade() {
        return grade;
    }

    public Roles getRole() {
        return role;
    }

    // ---------- Constructor ---------- //
    private Player() {
//        throw new UnsupportedOperationException();
    }

    public static final Player createRandomPlayer(int position, String shirtNumber, NameGenerator nameGenerator) {
        Player player = new Player();
        player.setRole(Roles.values()[position]);
        player.setNumber(shirtNumber);
        player.setGrade(RandomData.generateRandomNumber(30, 101));
        player.setName(nameGenerator.generateRandomName());
        return player;
    }

    // ---------- Additional Functions ---------- //
    public String toString() {
        return this.getNumber() + " " + this.getName() + " " + this.getRole() + " " + this.getGrade();
    }

    // ---------- Additional Static Factory Methods ---------- //
    public static final Player createNamelessPlayer(int position, String shirtNumber) {
        Player player = new Player();
        player.setRole(Roles.values()[position]);
        player.setNumber(shirtNumber);
        player.setGrade(RandomData.generateRandomNumber(30, 80));
        player.setName("");
        return player;
    }

    public static final Player createPerfectScorePlayer(int position, String shirtNumber, NameGenerator nameGenerator) {
        Player player = new Player();
        player.setRole(Roles.values()[position]);
        player.setNumber(shirtNumber);
        player.setGrade(100);
        player.setName(nameGenerator.generateRandomName());
        return player;
    }

    public static final Player createShirtlessPlayer(int position, String shirtNumber, NameGenerator nameGenerator) {
        Player player = new Player();
        player.setRole(Roles.values()[position]);
        player.setNumber(null);
        player.setGrade(RandomData.generateRandomNumber(30, 80));
        player.setName(nameGenerator.generateRandomName());
        return player;
    }
}
