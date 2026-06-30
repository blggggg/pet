package com.example.bv.newARG.walmart.task;

import com.example.bv.newARG.walmart.model.WalmartComplianceEvaluation;
import com.example.bv.newARG.walmart.model.WalmartReportContext;
import com.example.bv.newARG.walmart.service.WalmartReportDataService;

import java.util.List;
import java.util.Map;

public class ProcessWalmartComplianceEvaluationTask {
    private WalmartReportDataService walmartReportDataService;

    public ProcessWalmartComplianceEvaluationTask(WalmartReportDataService walmartReportDataService) {
        this.walmartReportDataService = walmartReportDataService;
    }

    public WalmartComplianceEvaluation execute(WalmartReportContext context, List<Map<String, String>> testList) {
        return walmartReportDataService.buildComplianceEvaluation(context, testList);
    }
}
