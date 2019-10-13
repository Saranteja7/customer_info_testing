package com.vzw.dvs.customerinfo.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class DMDDeviceResult {

    @JsonProperty
    private DeviceResultSet results;



    public DeviceResultSet getResults() {
        return results;
    }

    public void setResults(DeviceResultSet results) {
        this.results = results;
    }



}
