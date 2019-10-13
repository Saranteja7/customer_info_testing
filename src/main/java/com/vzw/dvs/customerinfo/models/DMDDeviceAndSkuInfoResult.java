package com.vzw.dvs.customerinfo.models;

import java.util.List;

public class DMDDeviceAndSkuInfoResult {
    List<DMDDeviceInfo> dmdDeviceInfos;
    List<DMDSkuInfo> dmdSkuInfos;

    public DMDDeviceAndSkuInfoResult() {
    }

    public DMDDeviceAndSkuInfoResult(List<DMDDeviceInfo> dmdDeviceInfos, List<DMDSkuInfo> dmdSkuInfos) {
        this.dmdDeviceInfos = dmdDeviceInfos;
        this.dmdSkuInfos = dmdSkuInfos;
    }

    public List<DMDDeviceInfo> getDmdDeviceInfos() {
        return dmdDeviceInfos;
    }

    public void setDmdDeviceInfos(List<DMDDeviceInfo> dmdDeviceInfos) {
        this.dmdDeviceInfos = dmdDeviceInfos;
    }

    public List<DMDSkuInfo> getDmdSkuInfos() {
        return dmdSkuInfos;
    }

    public void setDmdSkuInfos(List<DMDSkuInfo> dmdSkuInfos) {
        this.dmdSkuInfos = dmdSkuInfos;
    }
}
