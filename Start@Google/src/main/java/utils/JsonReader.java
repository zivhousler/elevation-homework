package utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public static List<String> getJSONFromFile(String filename) throws FileNotFoundException {
        List<String> jsonText = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Couldn't find this file in your library");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonText;
    }

    public static List<String> getJSONFromURL(String strUrl) throws IOException {
        List<String> jsonText = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(strUrl).openStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText.add(line);
            }
        } catch (MalformedURLException e) {
            throw new MalformedURLException("Couldn't create an object out of this url. Try again or another one!");
        }

        return jsonText;
    }
}
