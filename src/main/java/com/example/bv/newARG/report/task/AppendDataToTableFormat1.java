package com.example.bv.newARG.report.task;

import java.util.List;
import java.util.Map;

public class AppendDataToTableFormat1 {
    public String appendRows(List<Map<String, String>> rows) {
        StringBuilder html = new StringBuilder();
        if (rows == null) return "";
        for (Map<String, String> row : rows) {
            html.append("<tr>")
                .append("<td>").append(value(row.get("protocol"))).append("</td>")
                .append("<td>").append(value(row.get("testName"))).append("</td>")
                .append("<td>").append(value(row.get("result"))).append("</td>")
                .append("</tr>");
        }
        return html.toString();
    }

    private String value(String text) {
        return text == null ? "" : text;
    }
}
