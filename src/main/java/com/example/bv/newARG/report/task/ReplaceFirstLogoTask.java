package com.example.bv.newARG.report.task;

import com.example.bv.newARG.report.core.ReportTaskGeneric;
import com.example.bv.newARG.report.core.ReportTaskWrapper;
import com.example.bv.newARG.report.core.TaskParams;
import org.springframework.stereotype.Component;

/**
 * 页眉 Logo 处理：只替换第一个 Logo，避免影响共享图片。
 */
@Component
public class ReplaceFirstLogoTask extends ReportTaskGeneric {
    public void executeTask(TaskParams taskParams, ReportTaskWrapper objRTW) throws Exception {
        String logoPath = taskParams.get("logoPath");
        if (logoPath == null || logoPath.trim().length() == 0) {
            logoPath = "classpath:/static/images/bv-logo-gray.png";
        }
        objRTW.put("firstLogoOnly", Boolean.TRUE);
        objRTW.put("logoPath", logoPath);
        objRTW.put("logoPolicy", "原本是图片就换，原本不是图片不处理；只处理第一个 logo。 ");
    }
}
