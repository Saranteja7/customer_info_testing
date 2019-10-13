package com.vzw.dvs.customerinfo.models;

public class DMDDeviceInfo {

    private String deviceId;
    private String deviceIdType;
    private String deviceSku;
    private String imei1;
    private String imei2;

    public DMDDeviceInfo() {
    }

    public DMDDeviceInfo(String deviceId, String deviceIdType, String deviceSku, String imei1, String imei2) {
        this.deviceId = deviceId;
        this.deviceIdType = deviceIdType;
        this.deviceSku = deviceSku;
        this.imei1 = imei1;
        this.imei2 = imei2;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceIdType() {
        return deviceIdType;
    }

    public void setDeviceIdType(String deviceIdType) {
        this.deviceIdType = deviceIdType;
    }

    public String getDeviceSku() {
        return deviceSku;
    }

    public void setDeviceSku(String deviceSku) {
        this.deviceSku = deviceSku;
    }

    public String getImei1() {
        return imei1;
    }

    public void setImei1(String imei1) {
        this.imei1 = imei1;
    }

    public String getImei2() {
        return imei2;
    }

    public void setImei2(String imei2) {
        this.imei2 = imei2;
    }
}
