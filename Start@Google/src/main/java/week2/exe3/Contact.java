package week2.exe3;

import java.util.Objects;

public class Contact implements Cloneable, Comparable<Contact> {

    private Name name;
    private PhoneNumber phoneNumber;

    public Contact(Name name, PhoneNumber phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name=" + name +
                ", phoneNumber=" + phoneNumber +
                "}\n";
    }

    @Override
    protected Contact clone() {
        try {
            Contact clone = (Contact) super.clone();
            Name name = this.name.clone();
            PhoneNumber phoneNumber = this.phoneNumber.clone();
            clone.name = name;
            clone.phoneNumber = phoneNumber;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compareTo(Contact contact) {
        int result = this.name.compareTo(contact.name);
        if (result == 0) {
            return this.phoneNumber.compareTo(contact.phoneNumber);
        } else {
            return result;
        }
    }
}
