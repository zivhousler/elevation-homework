package week2.Exceptions;

import java.io.*;
import java.util.*;
import utils.JsonReader;

public class Config {

    Map<String, String> configMap;

    public Map<String, String> getMap() {
        try {
            return Objects.requireNonNull(this.configMap);
        } catch (NullPointerException e) {
            System.out.println("The list has not been initialized yet. Doing it right now!");
            initMap();
            return this.configMap;
        }
    }

    public void initMap() {
        this.configMap = new HashMap<>();
    }

    public void writeToConfig(Scanner myReader) {
        if (configMap == null) initMap();
        while (myReader.hasNextLine()) {
            String[] data = myReader.nextLine().split(":");
            configMap.put(data[0], data[1]);
        }
    }

    public void createNewDefaultFile(String fileName) throws IOException {
        System.out.println("Creating a default config file right now!");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("{\n");
            writer.write("name:ziv\n");
            writer.write("age:28\n");
            writer.write("gender:male\n");
            writer.write("}\n");
        } catch (IOException ex) {
            throw new IOException("Could not create a new file: " + ex);
        }
    }

    public void readFromFile(String fileName) throws IOException {
        if (configMap == null) initMap();
        try {
            List<String> textFromFile = JsonReader.getJSONFromFile(fileName);
            textFromFile.forEach(line -> System.out.println(line));
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find this file.");
            createNewDefaultFile(fileName);
        }
    }

    public String getDataByKey(String key) {
        try {
            return Objects.requireNonNull(configMap.get(key));
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("There's no such key in this file ," + e);
        }
    }
}
