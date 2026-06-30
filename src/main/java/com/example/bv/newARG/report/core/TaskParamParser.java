package com.example.bv.newARG.report.core;

public class TaskParamParser {
    private TaskParamParser() {}

    public static TaskParams parse(String text) {
        TaskParams params = new TaskParams();
        if (text == null || text.trim().length() == 0) {
            return params;
        }
        String[] pairs = text.split("&");
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];
            int idx = pair.indexOf('=');
            if (idx > -1) {
                params.getValues().put(pair.substring(0, idx), pair.substring(idx + 1));
            }
        }
        return params;
    }
}
