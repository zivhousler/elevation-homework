package week2.exe3;

import utils.NameGenerator;
import utils.RandomData;

import java.util.Objects;

public class Name implements Cloneable, Comparable<Name> {

    private Prefixes prefix;
    private String firstName;
    private String lastName;
    private static NameGenerator nameGenerator = new NameGenerator(RandomData.getFirstNameList(), RandomData.getLastNameList());

    public Name() {
        this.prefix = Prefixes.values()[RandomData.generateRandomNumber(0, Prefixes.values().length)];
        this.firstName = nameGenerator.getRandomFirstName();
        this.lastName = nameGenerator.getRandomLastName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return prefix == name.prefix && Objects.equals(firstName, name.firstName) && Objects.equals(lastName, name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Name{" +
                "prefix=" + prefix +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                "}\n\t";
    }

    @Override
    protected Name clone() {
        try {
            Name clone = (Name) super.clone();
            clone.prefix = this.prefix;
            clone.firstName = this.firstName;
            clone.lastName = this.lastName;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compareTo(Name name) {
        return (this.firstName + this.lastName).compareTo(name.firstName + name.lastName);
    }

    enum Prefixes {MR, MRS, MIS};
}

