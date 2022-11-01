package week2.exe3;

import java.util.ArrayList;

public class PhoneBook {

    private final ArrayList<Contact> phoneBook;
    private final String name;

    public PhoneBook(String name){
        this.phoneBook = new ArrayList<>();
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public ArrayList<Contact> getPhoneBook(){
        return this.phoneBook;
    }

    public void addContact(){
        phoneBook.add(new Contact(new Name(), new PhoneNumber()));
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "\nname='" + name + '\'' +
                "\nphoneBook=\n" + phoneBook +
                '}';
    }
}
