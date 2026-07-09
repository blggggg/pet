package com.example.bv.newARG.walmart.service;

import com.example.bv.newARG.walmart.model.WalmartComplianceEvaluation;
import com.example.bv.newARG.walmart.model.WalmartSupplementaryData;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.Map;

/**
 * Simple PDF generator using OpenPDF (already in pom.xml).
 */
public class WalmartCoverPdfGenerator {

    public static String generatePdf(Map<String, Object> cover, String outputPath) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outputPath));
        document.open();

        Font titleFont = new Font(Font.HELVETICA, 16, Font.BOLD);
        Font headFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font normal = new Font(Font.HELVETICA, 11, Font.NORMAL);

        document.add(new Paragraph("Walmart Report Cover", titleFont));
        document.add(new Paragraph(" "));

        // Evaluation
        Object evalObj = cover == null ? null : cover.get("evaluation");
        document.add(new Paragraph("Compliance Evaluation:", headFont));
        if (evalObj instanceof WalmartComplianceEvaluation) {
            WalmartComplianceEvaluation eval = (WalmartComplianceEvaluation) evalObj;
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            addCell(table, "Overall Rating", headFont);
            addCell(table, safe(eval.getOverallRating()), normal);
            addCell(table, "Performance Rating", headFont);
            addCell(table, safe(eval.getPerformanceRating()), normal);
            addCell(table, "Compliance Rating", headFont);
            addCell(table, safe(eval.getComplianceRating()), normal);
            addCell(table, "Packaging Rating", headFont);
            addCell(table, safe(eval.getPackagingRating()), normal);
            addCell(table, "Sustainability Rating", headFont);
            addCell(table, safe(eval.getSustainabilityRating()), normal);
            document.add(table);
        } else if (evalObj != null) {
            document.add(new Paragraph(String.valueOf(evalObj), normal));
        } else {
            document.add(new Paragraph("N/A", normal));
        }

        document.add(new Paragraph(" "));

        // Supplementary Data
        Object supObj = cover == null ? null : cover.get("supplementaryData");
        document.add(new Paragraph("Supplementary Data:", headFont));
        if (supObj instanceof WalmartSupplementaryData) {
            WalmartSupplementaryData sup = (WalmartSupplementaryData) supObj;
            document.add(new Paragraph("Disclaimer: " + safe(sup.getDisclaimer()), normal));
            document.add(new Paragraph("Report Cover Section: " + safe(sup.getReportCoverSection()), normal));
            document.add(new Paragraph("Footer Text: " + safe(sup.getFooterText()), normal));
        } else if (supObj != null) {
            document.add(new Paragraph(String.valueOf(supObj), normal));
        } else {
            document.add(new Paragraph("N/A", normal));
        }

        document.add(new Paragraph(" "));

        // Ratings map (if available via Walmart.getAllRatings)
        try {
            Map<String, String> ratings = com.example.bv.newARG.walmart.model.Walmart.getAllRatings();
            document.add(new Paragraph("All Ratings:", headFont));
            PdfPTable rtable = new PdfPTable(2);
            rtable.setWidthPercentage(100);
            for (Map.Entry<String, String> e : ratings.entrySet()) {
                addCell(rtable, e.getKey(), headFont);
                addCell(rtable, safe(e.getValue()), normal);
            }
            document.add(rtable);
        } catch (Throwable t) {
            // ignore
        }

        document.close();
        return outputPath;
    }

    private static void addCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(text == null ? "" : text, font));
        cell.setPadding(6);
        table.addCell(cell);
    }

    private static String safe(String s) {
        return s == null ? "" : s;
    }
}
