package io.gxstar.cleancode.refactoring;

public class StringUtils {

    public static boolean isNumeric(String substring) {
        try {
            Integer.parseInt(substring);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}