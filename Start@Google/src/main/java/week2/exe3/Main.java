package week2.exe3;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook("kalei kaluta");

        // Generate random contacts into the phone-book
        for (int i = 0; i < 5; i++) {
            phoneBook.addContact();
        }

        // Print all contacts
        System.out.println(phoneBook);
    }
}
