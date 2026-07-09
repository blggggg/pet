package com.example.bv.newARG.walmart.service;

import com.example.bv.newARG.walmart.model.WalmartReportContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple invoker to trigger Walmart cover processing.
 * Usage:
 *  - Run from IDE: run the main method
 *  - Or call new WalmartCoverInvoker().invoke(...) from other code
 */
public class WalmartCoverInvoker {

    /**
     * Standalone entry for quick testing.
     */
    public static void main(String[] args) {
        WalmartCoverInvoker invoker = new WalmartCoverInvoker();
        try {
            Map<String, Object> cover = invoker.invoke("16", "TL-1", false);
            System.out.println("Walmart cover result: " + cover);
            // print ratings map
            WalmartCoverService svc = new WalmartCoverService();
            System.out.println("Ratings: " + svc.getAllRatings());

            // generate PDF in project directory
            try {
                String out = System.getProperty("user.dir") + System.getProperty("file.separator") + "walmart_cover.pdf";
                WalmartCoverPdfGenerator.generatePdf(cover, out);
                System.out.println("PDF generated at: " + out);
            } catch (Exception e) {
                System.err.println("Failed to generate PDF: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Build minimal sample context and test rows, then call WalmartCoverService.
     * You can replace the sample data with real values.
     */
    public Map<String, Object> invoke(String protocol, String testLine, boolean transitGroup) {
        // prepare context
        WalmartReportContext ctx = new WalmartReportContext();
        ctx.setMasterSampleNo("MS-EXAMPLE-001");
        ctx.setProtocolVersionNumber(protocol);
        ctx.putAttribute("DISCLAIMER", "示例免责声明");

        // prepare a sample testList with one row (replace with real rows)
        List<Map<String, String>> testList = new ArrayList<Map<String, String>>();
        Map<String, String> row = new HashMap<String, String>();
        row.put("FAIL_CODE", "WM001");
        row.put("PROTOCOL", "WALMART-P16");
        row.put("PROTOCOL_CODE", "WMRT-GB-10060-USA");
        row.put("RESULT", "PASS");
        row.put("PRIVATE_BRAND_ARTWORK", "YES");
        row.put("MISC_3", "YES");
        testList.add(row);

        // call service (WalmartCoverService internally constructs needed tasks)
        WalmartCoverService service = new WalmartCoverService();
        return service.generateCover(protocol, testLine, transitGroup, ctx, testList);
    }
}
