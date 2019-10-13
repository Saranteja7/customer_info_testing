package com.vzw.dvs.customerinfo.models;

import java.util.List;

public class SkuResultSet {
    List<DMDSkuInfo> dmdSkuInfoForMissingDeviceIdsMP;

    public SkuResultSet(List<DMDSkuInfo> dmdSkuInfoForMissingDeviceIdsMP) {
        this.dmdSkuInfoForMissingDeviceIdsMP = dmdSkuInfoForMissingDeviceIdsMP;
    }

    public SkuResultSet() {
    }

    public List<DMDSkuInfo> getDmdSkuInfoForMissingDeviceIdsMP() {
        return dmdSkuInfoForMissingDeviceIdsMP;
    }

    public void setDmdSkuInfoForMissingDeviceIdsMP(List<DMDSkuInfo> dmdSkuInfoForMissingDeviceIdsMP) {
        this.dmdSkuInfoForMissingDeviceIdsMP = dmdSkuInfoForMissingDeviceIdsMP;
    }
}
