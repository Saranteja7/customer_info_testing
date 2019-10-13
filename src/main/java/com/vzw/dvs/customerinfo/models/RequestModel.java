package com.vzw.dvs.customerinfo.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestModel {

    @JsonProperty("requestType")
    private String requestType;

    @JsonProperty("keyAttributes")
    private Map<String, String> keyAttributes;
    @JsonProperty("correlationId")
    private String correlationId = "1";

    @JsonProperty("orginalService")
    private String orginalService = "1";

    @JsonProperty("orginalSubService")
    private String orginalSubService = "1";

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getOrginalService() {
        return orginalService;
    }

    public void setOrginalService(String orginalService) {
        this.orginalService = orginalService;
    }

    public String getOrginalSubService() {
        return orginalSubService;
    }

    public void setOrginalSubService(String orginalSubService) {
        this.orginalSubService = orginalSubService;
    }

    public RequestModel() {
    }

    public RequestModel(String requestType, Map<String, String> keyAttributes) {
        this.requestType = requestType;
        this.keyAttributes = keyAttributes;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Map<String, String> getKeyAttributes() {
        return keyAttributes;
    }

    public void setKeyAttributes(Map<String, String> keyAttributes) {
        this.keyAttributes = keyAttributes;
    }
}
