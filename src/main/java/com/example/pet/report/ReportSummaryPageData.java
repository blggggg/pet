package com.example.pet.dto.report;

import java.util.List;

public class ReportSummaryPageData {

    private String systemDate;
    private String loginUser;
    private String pageTitle;

    private String masterSampleNo;
    private String currentSampleNo;
    private Boolean includeNa;

    private List<String> sampleNoOptions;
    private List<String> protocolOptions;
    private String selectedProtocol;
    private List<String> screenOptions;

    private List<ReportSummaryStatDto> stats;
    private List<ReportSummaryTestRowDto> rows;

    public String getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(String systemDate) {
        this.systemDate = systemDate;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getMasterSampleNo() {
        return masterSampleNo;
    }

    public void setMasterSampleNo(String masterSampleNo) {
        this.masterSampleNo = masterSampleNo;
    }

    public String getCurrentSampleNo() {
        return currentSampleNo;
    }

    public void setCurrentSampleNo(String currentSampleNo) {
        this.currentSampleNo = currentSampleNo;
    }

    public Boolean getIncludeNa() {
        return includeNa;
    }

    public void setIncludeNa(Boolean includeNa) {
        this.includeNa = includeNa;
    }

    public List<String> getSampleNoOptions() {
        return sampleNoOptions;
    }

    public void setSampleNoOptions(List<String> sampleNoOptions) {
        this.sampleNoOptions = sampleNoOptions;
    }

    public List<String> getProtocolOptions() {
        return protocolOptions;
    }

    public void setProtocolOptions(List<String> protocolOptions) {
        this.protocolOptions = protocolOptions;
    }

    public String getSelectedProtocol() {
        return selectedProtocol;
    }

    public void setSelectedProtocol(String selectedProtocol) {
        this.selectedProtocol = selectedProtocol;
    }

    public List<String> getScreenOptions() {
        return screenOptions;
    }

    public void setScreenOptions(List<String> screenOptions) {
        this.screenOptions = screenOptions;
    }

    public List<ReportSummaryStatDto> getStats() {
        return stats;
    }

    public void setStats(List<ReportSummaryStatDto> stats) {
        this.stats = stats;
    }

    public List<ReportSummaryTestRowDto> getRows() {
        return rows;
    }

    public void setRows(List<ReportSummaryTestRowDto> rows) {
        this.rows = rows;
    }
}
