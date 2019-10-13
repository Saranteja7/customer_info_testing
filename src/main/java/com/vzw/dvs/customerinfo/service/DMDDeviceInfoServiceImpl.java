package com.vzw.dvs.customerinfo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vzw.dvs.customerinfo.models.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DMDDeviceInfoServiceImpl implements  DMDDeviceInfoService{
    private static final Logger logger = LogManager.getLogger(DMDDeviceInfoServiceImpl.class);

    Map keyAttr;

    @Value("${mtnUrl}")
    private String mtnUrl;

    @Autowired
    PNORestClient pnoRestClient;
    @Override
    public DMDDeviceResult findDeviceInfoByDeviceId(RequestModel requestModel) {
        DMDDeviceResult dmdDeviceResult = new DMDDeviceResult();
//		keyAttr.put("deviceId", "99000500946244"); //"99000500946244"
//		RequestModel requestModel = new RequestModel("test_CustInfo", keyAttr);
        requestModel.setCorrelationId("1234567890");
        requestModel.setOrginalService("multiDeviceLookup");
        requestModel.setOrginalSubService("NONE");
        StringBuilder sb = pnoRestClient.getPNOResponse(requestModel);

        ObjectMapper mapper = new ObjectMapper();
        try {
            if(sb.toString() != null && !StringUtils.isEmpty(sb.toString()))
                logger.info("Getting PNO response ");
            else
                logger.error("No response from PNO");
            dmdDeviceResult = mapper.readValue(sb.toString(), DMDDeviceResult.class);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error("IOException "+e.getMessage());
        }
        return dmdDeviceResult;
    }

    @Override
    public DMDDeviceAndSkuInfoResult getDeviceSkuInfo(DMDDeviceResult dmdDeviceResult) {
        RestTemplate restTemplate = new RestTemplate();
        List<DMDDeviceInfo> dmdDeviceInfoList = dmdDeviceResult.getResults().getDmdDeviceInfo();
        RequestModel requestModel;
        DMDDeviceAndSkuInfoResult dmdDeviceAndSkuInfoResult = new DMDDeviceAndSkuInfoResult();
        dmdDeviceAndSkuInfoResult.setDmdDeviceInfos(dmdDeviceResult.getResults().getDmdDeviceInfo());
        List<DMDSkuInfo> dmdSkuInfos = new ArrayList<>();

        for( int i=0; i<dmdDeviceInfoList.size();i++) {

            keyAttr = new HashMap<String, String>();
            if(dmdDeviceInfoList.get(i).getDeviceSku() == null) {
                continue;
            }
            keyAttr.put("deviceSku",dmdDeviceInfoList.get(i).getDeviceSku());
            requestModel = new RequestModel("test_MtnInfo",keyAttr);
            ResponseEntity<DMDSkuResult> response = restTemplate.postForEntity(mtnUrl,requestModel,DMDSkuResult.class);
            if(response.equals(null)){
                continue;
            }
            DMDSkuResult dmdSkuResult = response.getBody();
            dmdSkuInfos.addAll(dmdSkuResult.getResults().getDmdSkuInfoForMissingDeviceIdsMP());
        }
        dmdDeviceAndSkuInfoResult.setDmdSkuInfos(dmdSkuInfos);
        return dmdDeviceAndSkuInfoResult;
    }
}
