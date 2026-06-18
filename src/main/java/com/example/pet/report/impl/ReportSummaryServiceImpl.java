package com.example.pet.service.report.impl;

import com.example.pet.dto.report.ReportSummaryPageData;
import com.example.pet.dto.report.ReportSummaryStatDto;
import com.example.pet.dto.report.ReportSummaryTestRowDto;
import com.example.pet.service.report.ReportSummaryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReportSummaryServiceImpl implements ReportSummaryService {

    @Override
    public ReportSummaryPageData buildPageData() {
        ReportSummaryPageData data = new ReportSummaryPageData();

        data.setSystemDate("20260324");
        data.setLoginUser("MEENEP02 Z");
        data.setPageTitle("Report Data - Test Summary Level Rating");

        data.setMasterSampleNo("52260790036");
        data.setCurrentSampleNo("52260790036B (52260790047)");
        data.setIncludeNa(false);

        data.setSampleNoOptions(Arrays.asList(
                "52260790036B (52260790047)",
                "52260790036C (52260790048)",
                "52260790036D (52260790049)"
        ));

        data.setProtocolOptions(Arrays.asList(
                "(All)",
                "342 - CPSD-SL-00342-GBL-USA"
        ));
        data.setSelectedProtocol("342 - CPSD-SL-00342-GBL-USA");

        data.setScreenOptions(Arrays.asList(
                "[ Go To Other Screens ]",
                "Report Data",
                "Test Assignment",
                "Resulting"
        ));

        List<ReportSummaryStatDto> stats = new ArrayList<ReportSummaryStatDto>();
        stats.add(new ReportSummaryStatDto("Number of Assigned Tests", "4", false));
        stats.add(new ReportSummaryStatDto("Number of FAIL Test", "2", false));
        stats.add(new ReportSummaryStatDto("Number of PASS Test", "2", false));
        stats.add(new ReportSummaryStatDto("Number of DATA Test", "0", false));
        stats.add(new ReportSummaryStatDto("Number of NA Test", "0", false));
        stats.add(new ReportSummaryStatDto("Number of NOT TESTED Test", "0", false));
        stats.add(new ReportSummaryStatDto("Number of Tests with NA checkbox checked but without test rating input", "0", false));
        stats.add(new ReportSummaryStatDto("Number of Tests with NT checkbox checked but without test rating input", "0", false));
        stats.add(new ReportSummaryStatDto("Number of Tests with NR checkbox checked but without test rating input", "0", false));
        stats.add(new ReportSummaryStatDto("Number of Tests without NA/NT/NR checkbox checked and without test rating input", "0", true));
        data.setStats(stats);

        List<ReportSummaryTestRowDto> rows = new ArrayList<ReportSummaryTestRowDto>();

        ReportSummaryTestRowDto row1 = new ReportSummaryTestRowDto();
        row1.setChecked(false);
        row1.setNa(false);
        row1.setNt(false);
        row1.setNr(false);
        row1.setTestProperty("Small parts. (16 CFR 1501 (Mod))");
        row1.setCriteria("ADDED FOR REGRESSION TESTING");
        row1.setRating("PASS");
        row1.setRatingOptions(Arrays.asList("PASS", "FAIL", "DATA", "NA", "NT", "NR"));
        row1.setOrderNo("4001");
        row1.setActualTestCount("0");
        row1.setRemarkButtonText("RMK");
        row1.setUfpButtonText("UFP");
        rows.add(row1);

        ReportSummaryTestRowDto row2 = new ReportSummaryTestRowDto();
        row2.setChecked(false);
        row2.setNa(false);
        row2.setNt(false);
        row2.setNr(false);
        row2.setTestProperty("Dummy Test Colorfastness (10 CFR 430.32(a))");
        row2.setCriteria("Dummy test Criteria for Report");
        row2.setRating("FAIL");
        row2.setRatingOptions(Arrays.asList("PASS", "FAIL", "DATA", "NA", "NT", "NR"));
        row2.setOrderNo("9002");
        row2.setActualTestCount("0");
        row2.setRemarkButtonText("RMK");
        row2.setUfpButtonText("UFP");
        rows.add(row2);

        data.setRows(rows);
        return data;
    }
}
