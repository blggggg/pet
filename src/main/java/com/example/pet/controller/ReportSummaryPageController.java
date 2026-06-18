package com.example.pet.controller;

import com.example.pet.dto.report.ReportSummaryPageData;
import com.example.pet.service.report.ReportSummaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportSummaryPageController {

    private final ReportSummaryService reportSummaryService;

    public ReportSummaryPageController(ReportSummaryService reportSummaryService) {
        this.reportSummaryService = reportSummaryService;
    }

    @GetMapping("/report/test-summary-level-rating")
    public String page(Model model) {
        ReportSummaryPageData pageData = reportSummaryService.buildPageData();
        model.addAttribute("pageData", pageData);
        return "test-summary-level-rating";
    }
}
