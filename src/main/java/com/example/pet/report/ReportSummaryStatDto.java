package com.example.pet.dto.report;

public class ReportSummaryStatDto {

    private String label;
    private String value;
    private boolean danger;

    public ReportSummaryStatDto() {
    }

    public ReportSummaryStatDto(String label, String value, boolean danger) {
        this.label = label;
        this.value = value;
        this.danger = danger;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isDanger() {
        return danger;
    }

    public void setDanger(boolean danger) {
        this.danger = danger;
    }
}
