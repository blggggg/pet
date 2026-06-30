package com.example.bv.newARG.report.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ReportRenderService {
    @Resource private TemplateEngine templateEngine;

    public String renderHtml(String templateName, Map<String, Object> data) {
        Context context = new Context();
        if (data != null) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                context.setVariable(entry.getKey(), entry.getValue());
            }
        }
        return templateEngine.process(templateName, context);
    }
}
