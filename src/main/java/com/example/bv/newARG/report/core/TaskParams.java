package com.example.bv.newARG.report.core;

import java.util.HashMap;
import java.util.Map;

public class TaskParams {
    private String reportNo;
    private String templateCode;
    private String generationOption;
    private Map<String, String> values = new HashMap<String, String>();

    public String getReportNo() { return reportNo; }
    public void setReportNo(String reportNo) { this.reportNo = reportNo; }
    public String getTemplateCode() { return templateCode; }
    public void setTemplateCode(String templateCode) { this.templateCode = templateCode; }
    public String getGenerationOption() { return generationOption; }
    public void setGenerationOption(String generationOption) { this.generationOption = generationOption; }
    public Map<String, String> getValues() { return values; }
    public void setValues(Map<String, String> values) { this.values = values; }
    public String get(String key) { return values == null ? null : values.get(key); }
}
