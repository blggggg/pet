package com.example.bv.newARG.controller;

import com.example.bv.newARG.report.core.ResponseXML;
import com.example.bv.newARG.report.core.ReportTaskDispatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 报告生成统一入口。
 * 关联线索：用户文件库截图显示 ReportEngineTaskController.executeTasks 负责 generationOption、任务执行、签名等。
 */
@RestController
@RequestMapping("/report/task")
public class ReportEngineTaskController {

    @Resource
    private ReportTaskDispatcher reportTaskDispatcher;

    public Map<String, String> colorMap = new HashMap<String, String>();

    @RequestMapping("/executeTasks")
    public ResponseXML executeTasks(@RequestParam(value = "generationOption", required = false) String generationOption,
                                    @RequestParam(value = "reportNo", required = false) String reportNo,
                                    @RequestParam(value = "templateCode", required = false) String templateCode,
                                    @RequestParam(value = "taskParams", required = false) String taskParams,
                                    @RequestParam(value = "digitalSignature", required = false) String digitalSignature) {
        try {
            return reportTaskDispatcher.dispatch(generationOption, reportNo, templateCode, taskParams, digitalSignature);
        } catch (Exception ex) {
            updateErrorLog(reportNo, ex);
            return ResponseXML.error(ex.getMessage());
        }
    }

    private void updateErrorLog(String reportNo, Exception ex) {
        // 这里对接原系统错误日志表/文件日志。保留独立方法，便于与旧代码合并。
        System.err.println("Report generation failed. reportNo=" + reportNo + ", error=" + ex.getMessage());
    }
}
