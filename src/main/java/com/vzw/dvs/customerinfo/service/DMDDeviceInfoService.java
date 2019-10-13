package com.vzw.dvs.customerinfo.service;

import com.vzw.dvs.customerinfo.models.DMDDeviceAndSkuInfoResult;
import com.vzw.dvs.customerinfo.models.DMDDeviceResult;
import com.vzw.dvs.customerinfo.models.RequestModel;

public interface DMDDeviceInfoService {
    public DMDDeviceResult findDeviceInfoByDeviceId(RequestModel requestModel);
    public DMDDeviceAndSkuInfoResult getDeviceSkuInfo(DMDDeviceResult dmdDeviceResult);
}
