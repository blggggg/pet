package com.example.bv.newARG.walmart.task;

import com.example.bv.newARG.walmart.service.WalmartReportDataService;

public class WalmartReportTaskRegister {
    private WalmartReportDataService walmartReportDataService;

    public WalmartReportTaskRegister() {
        this.walmartReportDataService = new WalmartReportDataService();
    }

    public ProcessWalmartComplianceEvaluationTask complianceEvaluationTask() {
        return new ProcessWalmartComplianceEvaluationTask(walmartReportDataService);
    }

    public ProcessWalmartSupplementaryDataTask supplementaryDataTask() {
        return new ProcessWalmartSupplementaryDataTask(walmartReportDataService);
    }

    public ProcessWalmartCoverTask coverTask() {
        return new ProcessWalmartCoverTask(complianceEvaluationTask(), supplementaryDataTask());
    }
}
