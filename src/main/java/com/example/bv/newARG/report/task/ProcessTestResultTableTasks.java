package com.example.bv.newARG.report.task;

import com.example.bv.newARG.report.core.ReportTaskGeneric;
import com.example.bv.newARG.report.core.ReportTaskWrapper;
import com.example.bv.newARG.report.core.TaskParams;
import com.example.bv.newARG.report.service.MiscellaneousService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理 Test Result / Summary Rating 表。
 * 关联线索：用户文件库截图显示该类 extends ReportTaskGeneric，并调用 miscellaneousService.getRating(queryParams)。
 */
@Component
public class ProcessTestResultTableTasks extends ReportTaskGeneric {

    @Resource private MiscellaneousService miscellaneousService;

    public void executeTask(TaskParams taskParams, ReportTaskWrapper objRTW) throws Exception {
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("reportNo", taskParams.getReportNo());
        queryParams.put("templateCode", taskParams.getTemplateCode());
        queryParams.put("rating", taskParams.get("rating"));

        String rating = miscellaneousService.getRating(queryParams);
        objRTW.put("overallRating", rating);
        objRTW.put("resultTableRows", buildRows(taskParams, rating));

        if (objRTW.isDSNYGroup()) {
            String modVal = normalizeDsnyValue(taskParams.get("dsnyTestName"));
            objRTW.setModValDSNY(modVal);
        }
    }

    private List<Map<String, String>> buildRows(TaskParams taskParams, String rating) {
        List<Map<String, String>> rows = new ArrayList<Map<String, String>>();
        Map<String, String> row = new HashMap<String, String>();
        row.put("protocol", value(taskParams.get("protocol"), "Protocol"));
        row.put("testName", value(taskParams.get("testName"), "Test Name"));
        row.put("result", rating);
        rows.add(row);
        return rows;
    }

    private String normalizeDsnyValue(String value) {
        if (value == null) return "";
        return value.replace("DSNT", "DSNY").trim();
    }

    private String value(String text, String defaultValue) {
        return text == null || text.trim().length() == 0 ? defaultValue : text;
    }
}
