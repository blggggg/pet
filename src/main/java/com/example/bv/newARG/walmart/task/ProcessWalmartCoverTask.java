package com.example.bv.newARG.walmart.task;

import com.example.bv.newARG.walmart.model.WalmartComplianceEvaluation;
import com.example.bv.newARG.walmart.model.WalmartReportContext;
import com.example.bv.newARG.walmart.model.WalmartSupplementaryData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessWalmartCoverTask {
    private ProcessWalmartComplianceEvaluationTask complianceEvaluationTask;
    private ProcessWalmartSupplementaryDataTask supplementaryDataTask;

    public ProcessWalmartCoverTask(ProcessWalmartComplianceEvaluationTask complianceEvaluationTask,
                                   ProcessWalmartSupplementaryDataTask supplementaryDataTask) {
        this.complianceEvaluationTask = complianceEvaluationTask;
        this.supplementaryDataTask = supplementaryDataTask;
    }

    public ProcessWalmartCoverTask() {
    }

    public Map<String, Object> execute(WalmartReportContext context, List<Map<String, String>> testList) {
        WalmartComplianceEvaluation evaluation = complianceEvaluationTask.execute(context, testList);
        WalmartSupplementaryData supplementaryData = supplementaryDataTask.execute(context);

        Map<String, Object> reportCoverData = new HashMap<String, Object>();
        reportCoverData.put("evaluation", evaluation);
        reportCoverData.put("supplementaryData", supplementaryData);
        reportCoverData.put("disclaimer", supplementaryData.getDisclaimer());
        return reportCoverData;
    }
}
