package week4.AuthApp.controller;

import java.util.regex.Pattern;

public class InputValidation {
    public static boolean isValidPassword(String password) {
        return password.matches(".*[A-Z].*") && password.length() >= 6;
    }

    public static boolean isValidName(String name) {
        return name.matches("^[ A-Za-z]+$");
    }

    public static boolean isValidEmail(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$";

        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
