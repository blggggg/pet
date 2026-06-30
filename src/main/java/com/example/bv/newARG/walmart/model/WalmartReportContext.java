package com.example.bv.newARG.walmart.model;

import java.util.HashMap;
import java.util.Map;

public class WalmartReportContext {
    private String masterSampleNo;
    private Long companyId;
    private Long sampleTypeId;
    private Long locationId;
    private String protocolVersionNumber;
    private Map<String, Object> attributes = new HashMap<String, Object>();

    public String getMasterSampleNo() { return masterSampleNo; }
    public void setMasterSampleNo(String masterSampleNo) { this.masterSampleNo = masterSampleNo; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

    public Long getSampleTypeId() { return sampleTypeId; }
    public void setSampleTypeId(Long sampleTypeId) { this.sampleTypeId = sampleTypeId; }

    public Long getLocationId() { return locationId; }
    public void setLocationId(Long locationId) { this.locationId = locationId; }

    public String getProtocolVersionNumber() { return protocolVersionNumber; }
    public void setProtocolVersionNumber(String protocolVersionNumber) { this.protocolVersionNumber = protocolVersionNumber; }

    public Map<String, Object> getAttributes() { return attributes; }
    public void putAttribute(String key, Object value) { this.attributes.put(key, value); }
    public Object getAttribute(String key) { return this.attributes.get(key); }
}
