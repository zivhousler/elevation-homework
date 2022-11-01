package week2.Exceptions;

import java.io.*;

public class Main {
    public static void main(String... args) {

        Config configMap = new Config();

        try {
            System.out.println(configMap.getMap());
            configMap.readFromFile("filename.json");
            String value = configMap.getDataByKey("name");
            System.out.println(value);
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            System.out.println(e);
        }
    }


}
