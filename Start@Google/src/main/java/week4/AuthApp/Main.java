package week4.AuthApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);
//        testSystem();

    }
//
//    private static void testSystem() throws IOException {
//        Client client = new Client();
//        testRegistration(client);
//        testLogin(client);
//        testUpdate(client);
//        testDelete(client);
//    }
//
//    private static void testRegistration(Client client) {
//        System.out.println(">>>>>>>>> Registration of new users");
//
//        try {
//            client.register("lior.mathan@gmail.com", "Lior Mathan", "1234As");
//            client.register("Nitzan@gmail.com", "Nitzan Lahav", "987Nl11");
//            client.register("Elchanan@gmail.com", "Elchanan Madmon", "El9622");
//        } catch (Exception var3) {
//            System.out.println(var3.getMessage());
//        }
//
//    }
//
//    private static void testLogin(Client client) {
//        System.out.println(">>>>>>>>> Login of lior.mathan@gmail.com");
//
//        try {
//            client.login("lior.mathan@gmail.com", "1234As");
//        } catch (Exception var2) {
//            System.out.println(var2.getMessage());
//        }
//
//    }
//
//    private static void testUpdate(Client client) {
//        System.out.println(">>>>>>>>> Updating user name: Lior Mathan -> Ellie Mathan");
//
//        try {
//            client.updateUserName("lior.mathan@gmail.com", "Ellie Mathan");
//        } catch (Exception var5) {
//            System.out.println(var5.getMessage());
//        }
//
//        System.out.println(">>>>>>>>> Updating user email: \"lior.mathan@gmail.com\" -> \"ellie.mathan@gmail.com\"");
//
//        try {
//            client.updateUserEmail("lior.mathan@gmail.com", "ellie.mathan@gmail.com");
//        } catch (Exception var4) {
//            System.out.println(var4.getMessage());
//        }
//
//        System.out.println(">>>>>>>>> Updating user's password: 1234As -> TrustNo1");
//
//        try {
//            client.updateUserPassword("ellie.mathan@gmail.com", "TrustNo1");
//        } catch (Exception var3) {
//            System.out.println(var3.getMessage());
//        }
//    }
//
//    private static void testDelete(Client client) {
//        System.out.println(">>>>>>>>> Deleting user ellie.mathan@gmail.com");
//
//        try {
//            client.deleteUser("ellie.mathan@gmail.com");
//        } catch (Exception var3) {
//            System.out.println(var3.getMessage());
//        }
//
//    }
}