package com.example.bv.newARG.walmart.task;

import com.example.bv.newARG.walmart.model.WalmartReportContext;
import com.example.bv.newARG.walmart.model.WalmartSupplementaryData;
import com.example.bv.newARG.walmart.service.WalmartReportDataService;

public class ProcessWalmartSupplementaryDataTask {
    private WalmartReportDataService walmartReportDataService;

    public ProcessWalmartSupplementaryDataTask(WalmartReportDataService walmartReportDataService) {
        this.walmartReportDataService = walmartReportDataService;
    }

    public WalmartSupplementaryData execute(WalmartReportContext context) {
        return walmartReportDataService.buildSupplementaryData(context);
    }
}
