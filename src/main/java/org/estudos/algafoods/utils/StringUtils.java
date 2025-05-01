package org.estudos.algafoods.utils;

public class StringUtils {

    private StringUtils() {
    }

    public static String normalizeSpaces(String text) {
        return text == null ? null : text.trim().replaceAll("\\s+", " ");
    }
}
