package com.example.bv.newARG.report.task;

import com.example.bv.newARG.report.core.ReportTaskGeneric;
import com.example.bv.newARG.report.core.ReportTaskWrapper;
import com.example.bv.newARG.report.core.TaskParams;
import com.example.bv.newARG.report.service.MiscellaneousService;
import com.example.bv.newARG.report.util.ReportColorUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ProcessReportCoverTask extends ReportTaskGeneric {
    @Resource private MiscellaneousService miscellaneousService;
    @Resource private com.example.bv.newARG.walmart.service.WalmartCoverService walmartCoverService;

    public void executeTask(TaskParams taskParams, ReportTaskWrapper objRTW) throws Exception {
        String status = miscellaneousService.getReportStatus(taskParams.getReportNo());
        objRTW.put("coverStatus", status);
        objRTW.put("coverStatusColor", ReportColorUtil.statusColor(status));
        objRTW.put("reportCoverFieldLabelColor", "#2F5597");

        // Try to trigger Walmart cover processing and attach results to wrapper if available
        try {
            // Build WalmartReportContext
            com.example.bv.newARG.walmart.model.WalmartReportContext wctx = new com.example.bv.newARG.walmart.model.WalmartReportContext();
            wctx.setMasterSampleNo(taskParams.getReportNo());
            wctx.setProtocolVersionNumber(taskParams.get("protocolVersionNumber"));
            Object disclaimerVal = taskParams.get("DISCLAIMER");
            wctx.putAttribute("DISCLAIMER", disclaimerVal == null ? "" : disclaimerVal);

            // Read protocol/testLine/transitGroup from task params (fall back to sensible defaults)
            String protocol = taskParams.get("protocol") == null ? taskParams.get("protocolVersionNumber") : taskParams.get("protocol");
            if (protocol == null) protocol = "";
            String testLine = taskParams.get("testLine") == null ? "" : taskParams.get("testLine");
            boolean transitGroup = "true".equalsIgnoreCase(taskParams.get("transitGroup"));

            // Build minimal testList. If you have a data source, replace this with real rows.
            java.util.List<java.util.Map<String, String>> testList = buildWalmartTestList(taskParams);

            java.util.Map<String, Object> walmartCover = walmartCoverService.generateCover(protocol, testLine, transitGroup, wctx, testList);
            objRTW.put("walmartCover", walmartCover);
        } catch (Throwable t) {
            // Fail gracefully: attach error message for debugging, but don't break report generation
            objRTW.put("walmartError", t.getMessage());
        }
    }

    // Placeholder: currently returns an empty list. Replace implementation to fetch actual test rows.
    private java.util.List<java.util.Map<String, String>> buildWalmartTestList(TaskParams taskParams) {
        return new java.util.ArrayList<java.util.Map<String, String>>();
    }
}
