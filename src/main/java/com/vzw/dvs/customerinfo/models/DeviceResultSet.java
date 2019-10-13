package com.vzw.dvs.customerinfo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DeviceResultSet {
    List<DMDDeviceInfo> dmdDeviceInfo;

    public DeviceResultSet(List<DMDDeviceInfo> dmdDeviceInfo) {
        this.dmdDeviceInfo = dmdDeviceInfo;
    }

    public DeviceResultSet() {
    }

    public List getDmdDeviceInfo() {
        return dmdDeviceInfo;
    }

    public void setDmdDeviceInfo(List<DMDDeviceInfo> dmdDeviceInfo) {
        this.dmdDeviceInfo = dmdDeviceInfo;
    }
}
