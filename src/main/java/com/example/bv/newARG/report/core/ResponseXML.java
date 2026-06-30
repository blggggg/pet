package com.example.bv.newARG.report.core;

public class ResponseXML {
    private boolean success;
    private String code;
    private String message;
    private String data;

    public static ResponseXML ok(String data) {
        ResponseXML xml = new ResponseXML();
        xml.success = true;
        xml.code = "0";
        xml.message = "success";
        xml.data = data;
        return xml;
    }

    public static ResponseXML error(String message) {
        ResponseXML xml = new ResponseXML();
        xml.success = false;
        xml.code = "1";
        xml.message = message;
        return xml;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
}
