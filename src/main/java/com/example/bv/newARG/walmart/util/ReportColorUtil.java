package com.example.bv.newARG.walmart.util;

public final class ReportColorUtil {
    private ReportColorUtil() {}

    public static String getRatingColor(String rating) {
        if (StringSafeUtil.isEmpty(rating)) {
            return "";
        }

        String value = rating.trim().toUpperCase();

        if ("PASS".equals(value) || "GREEN".equals(value)) {
            return "GREEN";
        }
        if ("FAIL".equals(value) || "RED".equals(value)) {
            return "RED";
        }
        if ("NA".equals(value) || "N/A".equals(value)) {
            return "GRAY";
        }
        return "";
    }
}
