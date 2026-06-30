package com.example.bv.newARG.report.util;

import java.util.HashMap;
import java.util.Map;

public class ReportColorUtil {
    private static final Map<String, String> STATUS_COLOR = new HashMap<String, String>();
    static {
        STATUS_COLOR.put("PASS", "#00A651");
        STATUS_COLOR.put("FAIL", "#D71920");
        STATUS_COLOR.put("INCONCLUSIVE", "#D71920");
        STATUS_COLOR.put("DATA", "#808080");
        STATUS_COLOR.put("NOT APPLICABLE", "#808080");
    }

    private ReportColorUtil() {}

    public static String statusColor(String status) {
        if (status == null) return "#808080";
        String color = STATUS_COLOR.get(status.toUpperCase());
        return color == null ? "#808080" : color;
    }
}
