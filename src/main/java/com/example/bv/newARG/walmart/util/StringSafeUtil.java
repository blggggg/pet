package com.example.bv.newARG.walmart.util;

public final class StringSafeUtil {
    private StringSafeUtil() {}

    public static boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean equalsIgnoreCase(String a, String b) {
        if (a == null) {
            return b == null;
        }
        return a.equalsIgnoreCase(b);
    }

    public static String defaultIfEmpty(String value, String defaultValue) {
        return isEmpty(value) ? defaultValue : value;
    }
}
