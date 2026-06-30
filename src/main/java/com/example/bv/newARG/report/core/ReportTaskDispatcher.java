package com.example.bv.newARG.report.core;

import com.example.bv.newARG.report.task.ProcessReportCoverTask;
import com.example.bv.newARG.report.task.ProcessTestResultTableTasks;
import com.example.bv.newARG.report.task.ReplaceFirstLogoTask;
import com.example.bv.newARG.report.task.WaterlineTask;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ReportTaskDispatcher {
    @Resource private ProcessReportCoverTask processReportCoverTask;
    @Resource private ProcessTestResultTableTasks processTestResultTableTasks;
    @Resource private ReplaceFirstLogoTask replaceFirstLogoTask;
    @Resource private WaterlineTask waterlineTask;

    public ResponseXML dispatch(String generationOption, String reportNo, String templateCode, String taskParams, String digitalSignature) throws Exception {
        TaskParams params = TaskParamParser.parse(taskParams);
        params.setGenerationOption(generationOption);
        params.setReportNo(reportNo);
        params.setTemplateCode(templateCode);

        ReportTaskWrapper wrapper = new ReportTaskWrapper();
        wrapper.setReportNo(reportNo);
        wrapper.setTemplateCode(templateCode);

        processReportCoverTask.executeTask(params, wrapper);
        processTestResultTableTasks.executeTask(params, wrapper);
        replaceFirstLogoTask.executeTask(params, wrapper);
        waterlineTask.executeTask(params, wrapper);

        if (digitalSignature != null && digitalSignature.trim().length() > 0) {
            wrapper.put("digitalSignature", digitalSignature);
        }
        return ResponseXML.ok(String.valueOf(wrapper.get("outputPath")));
    }
}
