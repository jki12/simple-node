package com.example.Util;

public class Util {
    private Util() {
    }

    public static boolean isValid(String port) {
        try {
            int n = Integer.parseInt(port);

            if (n <= 0 || n >= 65536) return false;

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
