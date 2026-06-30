package com.example.bv.newARG.report.core;

import java.util.HashMap;
import java.util.Map;

public class ReportTaskWrapper {
    private String reportNo;
    private String templateCode;
    private String documentPath;
    private boolean dSNYGroup;
    private String modValDSNY;
    private Map<String, Object> context = new HashMap<String, Object>();

    public String getReportNo() { return reportNo; }
    public void setReportNo(String reportNo) { this.reportNo = reportNo; }
    public String getTemplateCode() { return templateCode; }
    public void setTemplateCode(String templateCode) { this.templateCode = templateCode; }
    public String getDocumentPath() { return documentPath; }
    public void setDocumentPath(String documentPath) { this.documentPath = documentPath; }
    public boolean isDSNYGroup() { return dSNYGroup; }
    public void setDSNYGroup(boolean dSNYGroup) { this.dSNYGroup = dSNYGroup; }
    public String getModValDSNY() { return modValDSNY; }
    public void setModValDSNY(String modValDSNY) { this.modValDSNY = modValDSNY; }
    public Map<String, Object> getContext() { return context; }
    public void put(String key, Object value) { context.put(key, value); }
    public Object get(String key) { return context.get(key); }
}
