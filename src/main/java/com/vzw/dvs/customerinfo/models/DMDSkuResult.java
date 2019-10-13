package com.vzw.dvs.customerinfo.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class DMDSkuResult {

    @JsonProperty
    private SkuResultSet results;



    public SkuResultSet getResults() {
        return results;
    }

    public void setResults(SkuResultSet results) {
        this.results = results;
    }



}
