package com.example.pet.dto.report;

import java.util.List;

public class ReportSummaryTestRowDto {

    private boolean checked;
    private boolean na;
    private boolean nt;
    private boolean nr;

    private String testProperty;
    private String criteria;
    private String rating;
    private List<String> ratingOptions;
    private String orderNo;
    private String actualTestCount;
    private String remarkButtonText;
    private String ufpButtonText;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isNa() {
        return na;
    }

    public void setNa(boolean na) {
        this.na = na;
    }

    public boolean isNt() {
        return nt;
    }

    public void setNt(boolean nt) {
        this.nt = nt;
    }

    public boolean isNr() {
        return nr;
    }

    public void setNr(boolean nr) {
        this.nr = nr;
    }

    public String getTestProperty() {
        return testProperty;
    }

    public void setTestProperty(String testProperty) {
        this.testProperty = testProperty;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<String> getRatingOptions() {
        return ratingOptions;
    }

    public void setRatingOptions(List<String> ratingOptions) {
        this.ratingOptions = ratingOptions;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getActualTestCount() {
        return actualTestCount;
    }

    public void setActualTestCount(String actualTestCount) {
        this.actualTestCount = actualTestCount;
    }

    public String getRemarkButtonText() {
        return remarkButtonText;
    }

    public void setRemarkButtonText(String remarkButtonText) {
        this.remarkButtonText = remarkButtonText;
    }

    public String getUfpButtonText() {
        return ufpButtonText;
    }

    public void setUfpButtonText(String ufpButtonText) {
        this.ufpButtonText = ufpButtonText;
    }
}
