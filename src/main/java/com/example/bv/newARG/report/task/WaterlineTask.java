package com.example.bv.newARG.report.task;

import com.example.bv.newARG.report.core.ReportTaskGeneric;
import com.example.bv.newARG.report.core.ReportTaskWrapper;
import com.example.bv.newARG.report.core.TaskParams;
import org.springframework.stereotype.Component;

/**
 * 水线规则：上方水线从 logo 右侧开始，贴近内容；表格底部/指定 addi 段下方不加。
 */
@Component
public class WaterlineTask extends ReportTaskGeneric {
    public void executeTask(TaskParams taskParams, ReportTaskWrapper objRTW) throws Exception {
        objRTW.put("waterlineEnabled", Boolean.TRUE);
        objRTW.put("waterlineStart", "afterLogoRightEdge");
        objRTW.put("skipWaterlineAfterTable", Boolean.TRUE);
        objRTW.put("skipWaterlineAfterAddiSection", Boolean.TRUE);
    }
}
