package com.example.bv.newARG.report.util;

public class StringSafeUtil {
    private StringSafeUtil() {}

    public static String nvl(String value, String defaultValue) {
        return value == null || value.trim().length() == 0 ? defaultValue : value;
    }
}
