package tech.lastbox.util;

public class UserDataValidation {

    public static boolean isValidPassword(String rawPassword) {
        return rawPassword != null && rawPassword.length() >= 8;
    }

    public static boolean isValidUsername(String username) {
        return !(username == null || username.isEmpty());
    }
}
