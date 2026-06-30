package com.example.bv.newARG.report.service;

import org.springframework.stereotype.Service;

@Service
public class ReportTemplateService {
    public String resolveTemplate(String templateCode) {
        if (templateCode == null || templateCode.trim().length() == 0) {
            return "report/test-summary-level-rating";
        }
        return "report/" + templateCode;
    }
}
