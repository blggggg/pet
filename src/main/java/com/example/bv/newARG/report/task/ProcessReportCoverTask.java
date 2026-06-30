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

    public void executeTask(TaskParams taskParams, ReportTaskWrapper objRTW) throws Exception {
        String status = miscellaneousService.getReportStatus(taskParams.getReportNo());
        objRTW.put("coverStatus", status);
        objRTW.put("coverStatusColor", ReportColorUtil.statusColor(status));
        objRTW.put("reportCoverFieldLabelColor", "#2F5597");
    }
}
