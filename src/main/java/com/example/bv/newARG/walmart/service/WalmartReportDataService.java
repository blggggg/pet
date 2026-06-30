package com.example.bv.newARG.walmart.service;

import com.example.bv.newARG.walmart.model.WalmartComplianceEvaluation;
import com.example.bv.newARG.walmart.model.WalmartReportContext;
import com.example.bv.newARG.walmart.model.WalmartSupplementaryData;
import com.example.bv.newARG.walmart.util.StringSafeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WalmartReportDataService {

    public WalmartComplianceEvaluation buildComplianceEvaluation(WalmartReportContext context, List<Map<String, String>> testList) {
        WalmartComplianceEvaluation evaluation = new WalmartComplianceEvaluation();
        evaluation.setPerformanceRating(calculatePerformanceRating(testList));
        evaluation.setComplianceRating(calculateComplianceRating(testList));
        evaluation.setPackagingRating(calculatePackagingRating(context, testList));
        evaluation.setSustainabilityRating(calculateSustainabilityRating(testList));
        evaluation.setOverallRating(calculateOverallRating(evaluation));
        return evaluation;
    }

    public WalmartSupplementaryData buildSupplementaryData(WalmartReportContext context) {
        WalmartSupplementaryData data = new WalmartSupplementaryData();

        // Real implementation:
        // 1. Read Report Cover Table section 4 for e-Filing.
        // 2. Read Disclaimer from MISC_SETUP by companyId/sampleTypeId/locationId/propertyId.
        // 3. Put the text into report rendering object.
        Object disclaimer = context.getAttribute("DISCLAIMER");

        data.setDisclaimer(disclaimer == null ? "" : String.valueOf(disclaimer));
        data.setReportCoverSection("4");
        return data;
    }

    private String calculatePerformanceRating(List<Map<String, String>> testList) {
        List<Map<String, String>> filteredList = new ArrayList<Map<String, String>>();
        for (Map<String, String> test : safeList(testList)) {
            String failCode = test.get("FAIL_CODE");
            if (StringSafeUtil.isNotEmpty(failCode) && failCode.startsWith("WM")) {
                filteredList.add(test);
            }
        }
        return calculateRatingOrNA(filteredList);
    }

    private String calculateComplianceRating(List<Map<String, String>> testList) {
        List<Map<String, String>> filteredList = new ArrayList<Map<String, String>>();
        for (Map<String, String> test : safeList(testList)) {
            String failCode = test.get("FAIL_CODE");
            if (StringSafeUtil.isNotEmpty(failCode) && !failCode.startsWith("WM") && !failCode.startsWith("-J")) {
                filteredList.add(test);
            }
        }
        return calculateRatingOrNA(filteredList);
    }

    private String calculatePackagingRating(WalmartReportContext context, List<Map<String, String>> testList) {
        List<Map<String, String>> filteredList = new ArrayList<Map<String, String>>();
        for (Map<String, String> test : safeList(testList)) {
            String protocol = test.get("PROTOCOL");
            String privateBrandArtwork = test.get("PRIVATE_BRAND_ARTWORK");
            String misc3 = test.get("MISC_3");

            boolean walmartProtocol = StringSafeUtil.isNotEmpty(protocol) && protocol.toUpperCase().contains("WALMART");
            boolean privateBrandArtworkYes = "YES".equalsIgnoreCase(privateBrandArtwork) || "YES".equalsIgnoreCase(misc3);

            if (walmartProtocol && privateBrandArtworkYes) {
                filteredList.add(test);
            }
        }
        return calculateRatingOrNA(filteredList);
    }

    private String calculateSustainabilityRating(List<Map<String, String>> testList) {
        List<Map<String, String>> filteredList = new ArrayList<Map<String, String>>();
        for (Map<String, String> test : safeList(testList)) {
            String protocolCode = test.get("PROTOCOL_CODE");
            if ("WMRT-GB-10060-USA".equalsIgnoreCase(protocolCode)) {
                filteredList.add(test);
            }
        }
        return calculateRatingOrNA(filteredList);
    }

    private String calculateOverallRating(WalmartComplianceEvaluation evaluation) {
        if ("FAIL".equalsIgnoreCase(evaluation.getPerformanceRating())
                || "FAIL".equalsIgnoreCase(evaluation.getComplianceRating())
                || "FAIL".equalsIgnoreCase(evaluation.getPackagingRating())
                || "FAIL".equalsIgnoreCase(evaluation.getSustainabilityRating())) {
            return "FAIL";
        }
        return "PASS";
    }

    private String calculateRatingOrNA(List<Map<String, String>> filteredList) {
        if (filteredList == null || filteredList.isEmpty()) {
            return "NA";
        }
        for (Map<String, String> test : filteredList) {
            String result = test.get("RESULT");
            if ("FAIL".equalsIgnoreCase(result)) {
                return "FAIL";
            }
        }
        return "PASS";
    }

    private List<Map<String, String>> safeList(List<Map<String, String>> list) {
        return list == null ? new ArrayList<Map<String, String>>() : list;
    }
}
