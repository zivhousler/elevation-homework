package week1;

import utils.NameGenerator;
import utils.RandomData;

public class Player {
    // ---------- Properties ---------- //
    private String name;
    private Integer number;
    private int grade;
    private Roles role;

    private static enum Roles {GOAL_KEEPER, DEFENDER, MIDFIELDER, ATTACKER}

    // ---------- Setters ---------- //
    private void setRole(Roles role) {
        this.role = role;
    }

    private void setName(String name) {
        if (name.length() < 2)
            throw new IllegalArgumentException("Player's name can't be shorter than 2 letters");
        this.name = name;
    }

    private void setNumber(Integer number) {
        if (number <= 0)
            throw new IllegalArgumentException("Player's shirt number must be a positive number larger than 0");
        this.number = number;
    }

    private void setGrade(int grade) {
        if (grade < 0 || grade > 100)
            throw new IllegalArgumentException("Player's score must be a positive number between 0 to 100");
        this.grade = grade;
    }

    // ---------- Getters ---------- //
    public String getName() {
        return name;
    }

    public Integer getNumber() {
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
    }

    public static final Player createRandomPlayer(int position, Integer shirtNumber, NameGenerator nameGenerator) {
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
    public static final Player createNamelessPlayer(int position, Integer shirtNumber) {
        Player player = new Player();
        player.setRole(Roles.values()[position]);
        player.setNumber(shirtNumber);
        player.setGrade(RandomData.generateRandomNumber(30, 80));
        return player;
    }

    public static final Player createPerfectScorePlayer(int position, Integer shirtNumber, NameGenerator nameGenerator) {
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
