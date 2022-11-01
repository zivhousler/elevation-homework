package utils;

public class NameGenerator {
    // ---------- Properties ---------- //
    private final String[] firstName;
    private final String[] lastName;

    // ---------- Constructor ---------- //
    public NameGenerator(String[] firstName, String[] lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // ---------- Additional Functions ---------- //
    public String getRandomFirstName() {
        int boundedRandomValue = RandomData.generateRandomNumber(0, this.firstName.length);
        return this.firstName[boundedRandomValue];
    }

    public String getRandomLastName() {
        int boundedRandomValue = RandomData.generateRandomNumber(0, this.lastName.length);
        return this.lastName[boundedRandomValue];
    }

    public String generateRandomName() {
        return getRandomFirstName() + " " + getRandomLastName();
    }}
