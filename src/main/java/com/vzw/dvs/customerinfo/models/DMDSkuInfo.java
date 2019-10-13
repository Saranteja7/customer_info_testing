package com.vzw.dvs.customerinfo.models;

public class DMDSkuInfo {
    private String deviceSku;
    private String createdBy;
    private String mfgCode;
    private String prodName;
    private String createdTmstamp;
    private String effectiveDate;

    public DMDSkuInfo() {
    }

    public DMDSkuInfo(String deviceSku, String createdBy, String mfgCode, String prodName, String createdTmstamp, String effectiveDate) {
        this.deviceSku = deviceSku;
        this.createdBy = createdBy;
        this.mfgCode = mfgCode;
        this.prodName = prodName;
        this.createdTmstamp = createdTmstamp;
        this.effectiveDate = effectiveDate;
    }

    public String getCreatedTmstamp() {
        return createdTmstamp;
    }

    public void setCreatedTmstamp(String createdTmstamp) {
        this.createdTmstamp = createdTmstamp;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getDeviceSku() {
        return deviceSku;
    }

    public void setDeviceSku(String deviceSku) {
        this.deviceSku = deviceSku;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getMfgCode() {
        return mfgCode;
    }

    public void setMfgCode(String mfgCode) {
        this.mfgCode = mfgCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
}
