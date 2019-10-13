package com.vzw.dvs.customerinfo.controller;

import com.vzw.dvs.customerinfo.models.DMDDeviceAndSkuInfoResult;
import com.vzw.dvs.customerinfo.models.DMDDeviceResult;
import com.vzw.dvs.customerinfo.models.RequestModel;
import com.vzw.dvs.customerinfo.service.DMDDeviceInfoService;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.IOException;

@RestController
@RefreshScope
@RequestMapping("/customer_info")
public class CustomerInfoController {


	@Value("${message}")
	private String message;

	@Autowired
	DMDDeviceInfoService dmdDeviceInfoService;

	@GetMapping(value = "/getmessage")
	public String Hiii()
	{
		return message;
	}
	@PostMapping(value = "/findbyid")
	public DMDDeviceAndSkuInfoResult findById(@RequestBody RequestModel requestModel) {
		DMDDeviceResult dmdDeviceResult = dmdDeviceInfoService.findDeviceInfoByDeviceId(requestModel);
		DMDDeviceAndSkuInfoResult dmdDeviceAndSkuInfoResult = dmdDeviceInfoService.getDeviceSkuInfo(dmdDeviceResult);
		return dmdDeviceAndSkuInfoResult;
	}
	@GetMapping(value = "/getversion")
	public String getVersion()throws IOException, XmlPullParserException {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model project = reader.read(new FileReader("pom.xml"));
		return "Current application version is : "+project.getVersion();
	}

}
