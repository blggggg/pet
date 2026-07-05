package com.example.bv.newARG.walmart.service;

import com.example.bv.newARG.walmart.model.Walmart;
import com.example.bv.newARG.walmart.task.ProcessWalmartCoverTask;

public class WalmartFailure extends ProcessWalmartCoverTask {

    public void execute() {

        // 1. Transit Group 直接跳过
        if (Walmart.isWalmartTransitGroup) {
            return;
        }

        // 2. 获取基础信息
        String protocol = Walmart.getProtocol();
        String testLine = Walmart.getTestLine();

        boolean isProtocol16 = "16".equals(protocol);
        boolean isProtocol17 = "17".equals(protocol);

        // 3. 默认值
        String packagingRating = "N/A";
        String sustainabilityRating = "N/A";
        String qualityRating = "N/A";
        String chemicalRating = "N/A";
        String socialRating = "N/A";
        String securityRating = "N/A";
        String complicatingFactor = "N/A";

        // 4. 通用 rating
        qualityRating = getQualityRating();
        chemicalRating = getChemicalRating();
        socialRating = getSocialRating();
        securityRating = getSecurityRating();

        // 5. Walmart 特殊 rating
        if (isProtocol16 || isProtocol17) {
            packagingRating = getPackagingRatingByProtocol(protocol);
            sustainabilityRating = getSustainabilityRatingByProtocol(protocol);
        }

        // 6. complicating factor
        complicatingFactor = Walmart.getComplicatingFactor();

        // 7. 如果有特殊 NA 逻辑，统一兜底
        packagingRating = normalizeRating(packagingRating);
        sustainabilityRating = normalizeRating(sustainabilityRating);
        qualityRating = normalizeRating(qualityRating);
        chemicalRating = normalizeRating(chemicalRating);
        socialRating = normalizeRating(socialRating);
        securityRating = normalizeRating(securityRating);
        complicatingFactor = normalizeRating(complicatingFactor);

        // 8. 写入报告对象 / cover table
        setRating("PACKAGING_RATING", packagingRating);
        setRating("SUSTAINABILITY_RATING", sustainabilityRating);
        setRating("QUALITY_RATING", qualityRating);
        setRating("CHEMICAL_RATING", chemicalRating);
        setRating("SOCIAL_RATING", socialRating);
        setRating("SECURITY_RATING", securityRating);
        setRating("COMPLICATING_FACTOR", complicatingFactor);
    }

    private String getPackagingRatingByProtocol(String protocol) {
        if ("16".equals(protocol)) {
            return Walmart.getPackagingRatingForProtocol16();
        }

        if ("17".equals(protocol)) {
            return Walmart.getPackagingRatingForProtocol17();
        }

        return "N/A";
    }

    private String getSustainabilityRatingByProtocol(String protocol) {
        if ("16".equals(protocol)) {
            return Walmart.getSustainabilityRatingForProtocol16();
        }

        if ("17".equals(protocol)) {
            return Walmart.getSustainabilityRatingForProtocol17();
        }

        return "N/A";
    }

    private String normalizeRating(String rating) {
        if (rating == null || rating.trim().length() == 0) {
            return "N/A";
        }

        return rating.trim();
    }

    private String getQualityRating() {
        return Walmart.getQualityRating();
    }

    private String getChemicalRating() {
        return Walmart.getChemicalRating();
    }

    private String getSocialRating() {
        return Walmart.getSocialRating();
    }

    private String getSecurityRating() {
        return Walmart.getSecurityRating();
    }

    private void setRating(String key, String value) {
        Walmart.setRating(key, value);
    }
}
