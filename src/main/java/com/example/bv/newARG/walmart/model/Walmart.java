package com.example.bv.newARG.walmart.model;

import com.example.bv.newARG.walmart.service.WalmartReportDataService;




import java.util.HashMap;
import java.util.Map;

public class Walmart {

    public static boolean isWalmartTransitGroup;

    private static String protocol;
    private static String testLine;
    private static String complicatingFactor;

    private static final String NA = "N/A";

    public static final String PACKAGING_RATING = "PACKAGING_RATING";
    public static final String SUSTAINABILITY_RATING = "SUSTAINABILITY_RATING";
    public static final String QUALITY_RATING = "QUALITY_RATING";
    public static final String CHEMICAL_RATING = "CHEMICAL_RATING";
    public static final String SOCIAL_RATING = "SOCIAL_RATING";
    public static final String SECURITY_RATING = "SECURITY_RATING";
    public static final String COMPLICATING_FACTOR = "COMPLICATING_FACTOR";

    private static final Map<String, String> ratingMap = new HashMap<String, String>();

    private Walmart() {
    }

    /**
     * 初始化 Walmart 报告上下文
     * 实际项目里建议在生成 Walmart cover 前调用一次
     */
    public static void init(String protocolValue,
                            String testLineValue,
                            boolean transitGroup) {

        protocol = safe(protocolValue);
        testLine = safe(testLineValue);
        isWalmartTransitGroup = transitGroup;

        complicatingFactor = NA;
        ratingMap.clear();
    }

    public static String getProtocol() {
        return protocol;
    }

    public static void setProtocol(String protocolValue) {
        protocol = safe(protocolValue);
    }

    public static String getTestLine() {
        return testLine;
    }

    public static void setTestLine(String testLineValue) {
        testLine = safe(testLineValue);
    }

    public static boolean isProtocol16() {
        return "16".equals(protocol);
    }

    public static boolean isProtocol17() {
        return "17".equals(protocol);
    }

    public static boolean isProtocol16Or17() {
        return isProtocol16() || isProtocol17();
    }

    public static String getComplicatingFactor() {
        return normalize(complicatingFactor);
    }

    public static void setComplicatingFactor(String value) {
        complicatingFactor = normalize(value);
        setRating(COMPLICATING_FACTOR, complicatingFactor);
    }

    public static String getPackagingRating() {
        if (!isProtocol16Or17()) {
            return NA;
        }

        if (isProtocol16()) {
            return getPackagingRatingForProtocol16();
        }

        if (isProtocol17()) {
            return getPackagingRatingForProtocol17();
        }

        return NA;
    }

    public static String getPackagingRatingForProtocol16() {
        // TODO 这里替换成你项目里 protocol 16 的真实计算逻辑
        
        String value = getRating("PACKAGING_RATING_P16");

        if (isEmpty(value)) {
            value = getRating(PACKAGING_RATING);
        }

        return normalize(value);
    }

    public static String getPackagingRatingForProtocol17() {
        // TODO 这里替换成你项目里 protocol 17 的真实计算逻辑
        String value = getRating("PACKAGING_RATING_P17");

        if (isEmpty(value)) {
            value = getRating(PACKAGING_RATING);
        }

        return normalize(value);
    }

    public static String getSustainabilityRating() {
        if (!isProtocol16Or17()) {
            return NA;
        }

        if (isProtocol16()) {
            return getSustainabilityRatingForProtocol16();
        }

        if (isProtocol17()) {
            return getSustainabilityRatingForProtocol17();
        }

        return NA;
    }

    public static String getSustainabilityRatingForProtocol16() {
        // TODO 这里替换成你项目里 protocol 16 的真实计算逻辑
        String value = getRating("SUSTAINABILITY_RATING_P16");

        if (isEmpty(value)) {
            value = getRating(SUSTAINABILITY_RATING);
        }

        return normalize(value);
    }

    public static String getSustainabilityRatingForProtocol17() {
        // TODO 这里替换成你项目里 protocol 17 的真实计算逻辑
        String value = getRating("SUSTAINABILITY_RATING_P17");

        if (isEmpty(value)) {
            value = getRating(SUSTAINABILITY_RATING);
        }

        return normalize(value);
    }

    public static String getQualityRating() {
        return normalize(getRating(QUALITY_RATING));
    }

    public static String getChemicalRating() {
        return normalize(getRating(CHEMICAL_RATING));
    }

    public static String getSocialRating() {
        return normalize(getRating(SOCIAL_RATING));
    }

    public static String getSecurityRating() {
        return normalize(getRating(SECURITY_RATING));
    }

    public static void setRating(String key, String value) {
        if (isEmpty(key)) {
            return;
        }

        ratingMap.put(key, normalize(value));
    }

    public static String getRating(String key) {
        if (isEmpty(key)) {
            return NA;
        }

        return normalize(ratingMap.get(key));
    }

    public static Map<String, String> getAllRatings() {
        Map<String, String> result = new HashMap<String, String>();

        result.put(PACKAGING_RATING, getPackagingRating());
        result.put(SUSTAINABILITY_RATING, getSustainabilityRating());
        result.put(QUALITY_RATING, getQualityRating());
        result.put(CHEMICAL_RATING, getChemicalRating());
        result.put(SOCIAL_RATING, getSocialRating());
        result.put(SECURITY_RATING, getSecurityRating());
        result.put(COMPLICATING_FACTOR, getComplicatingFactor());

        return result;
    }

    private static String normalize(String value) {
        if (isEmpty(value)) {
            return NA;
        }

        return value.trim();
    }

    private static String safe(String value) {
        if (value == null) {
            return "";
        }

        return value.trim();
    }

    private static boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }
}