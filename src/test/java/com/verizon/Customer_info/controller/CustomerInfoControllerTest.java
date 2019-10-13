package com.verizon.Customer_info.controller;

import com.vzw.dvs.customerinfo.CustomerInfoApplication;
import com.vzw.dvs.customerinfo.controller.CustomerInfoController;
import com.vzw.dvs.customerinfo.models.*;
import com.vzw.dvs.customerinfo.service.DMDDeviceInfoService;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CustomerInfoApplication.class)
@WebMvcTest(value = CustomerInfoController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class CustomerInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DMDDeviceInfoService dmdDeviceInfoService;

    Map<String, String> keyAttr = new HashMap<>();


    DMDDeviceInfo dmdDeviceInfo1 = new DMDDeviceInfo("123456","VOIP","LG-123",null,null);
    List<DMDDeviceInfo> dmdDeviceInfoList = new ArrayList<>();
    DeviceResultSet deviceResultSet = new DeviceResultSet();
    DMDDeviceResult dmdDeviceResult = new DMDDeviceResult();



    DMDSkuInfo dmdSkuInfo1 = new DMDSkuInfo("LG-123","author1","mfgcode1","prodname1","timestamp1","11/11/1111");
    List<DMDSkuInfo> dmdSkuInfoList = new ArrayList<>();




    DMDDeviceAndSkuInfoResult dmdDeviceAndSkuInfoResult = new DMDDeviceAndSkuInfoResult();

    @Test
    public void findByDeviceId() throws Exception {
        keyAttr.put("deviceId","123456");


        dmdDeviceInfoList.add(dmdDeviceInfo1);

        deviceResultSet.setDmdDeviceInfo(dmdDeviceInfoList);

        dmdDeviceResult.setResults(deviceResultSet);


        dmdSkuInfoList.add(dmdSkuInfo1);


        dmdDeviceAndSkuInfoResult.setDmdDeviceInfos(dmdDeviceInfoList);
        dmdDeviceAndSkuInfoResult.setDmdSkuInfos(dmdSkuInfoList);


        Mockito.when(dmdDeviceInfoService.findDeviceInfoByDeviceId(Mockito.any(RequestModel.class))).thenReturn(dmdDeviceResult);

        Mockito.when(dmdDeviceInfoService.getDeviceSkuInfo(Mockito.any(DMDDeviceResult.class))).thenReturn(dmdDeviceAndSkuInfoResult);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/customer_info/findbyid")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"requestType\": \"test_CustInfo\",\"keyAttributes\": {\"deviceId\": \"123456\"}}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{" +
                                "\"dmdDeviceInfos\":[" +
                                    "{" +
                                        "\"deviceId\":\"123456\"," +
                                        "\"deviceIdType\":\"VOIP\"," +
                                        "\"deviceSku\":\"LG-123\"," +
                                        "\"imei1\":null,\"imei2\":null" +
                                    "}" +
                                "]," +
                                "\"dmdSkuInfos\":[" +
                                    "{" +
                                        "\"deviceSku\":\"LG-123\"," +
                                        "\"createdBy\":\"author1\"," +
                                        "\"mfgCode\":\"mfgcode1\"," +
                                        "\"prodName\":\"prodname1\"," +
                                        "\"createdTmstamp\":\"timestamp1\"," +
                                        "\"effectiveDate\":\"11/11/1111\"" +
                                    "}" +
                                "]" +
                            "}";
        Assert.assertEquals(expected,result.getResponse().getContentAsString());
    }

    @Test
    public void getVersion() throws Exception {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model project = reader.read(new FileReader("pom.xml"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer_info/getversion")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals("Current application version is : "+project.getVersion(),result.getResponse().getContentAsString());
    }
}
