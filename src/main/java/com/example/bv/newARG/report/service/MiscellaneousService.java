package com.example.bv.newARG.report.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MiscellaneousService {
    public String getRating(Map<String, String> queryParams) {
        String value = queryParams == null ? null : queryParams.get("rating");
        return value == null || value.trim().length() == 0 ? "PASS" : value;
    }

    public String getReportStatus(String reportNo) {
        return "PASS";
    }
}
