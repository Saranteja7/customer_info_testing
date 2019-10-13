package com.vzw.dvs.customerinfo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vzw.dvs.customerinfo.models.RequestModel;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

@Component
@RefreshScope
public class PNORestClient {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Value("${pnoUrl}")
    private String pnoUrl;

    CloseableHttpClient client;



    public StringBuilder getPNOResponse(RequestModel requestModel) {

        BufferedReader br = null;
        try {
            ObjectMapper mapper = new ObjectMapper();

            client = HttpClients.custom().build();
            HttpPost httpPost = new HttpPost(pnoUrl);
            String jsonInString = mapper.writeValueAsString(requestModel);
            logger.info(jsonInString);
            StringEntity entity = new StringEntity(jsonInString);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("CORRELATION_ID", requestModel.getCorrelationId());
            httpPost.setHeader("ORIGINAL_SERVICE", requestModel.getOrginalService());
            httpPost.setHeader("ORIGINAL_SubSERVICE", requestModel.getOrginalSubService());

            Calendar b4 = Calendar.getInstance();
            long before = b4.getTimeInMillis();

            CloseableHttpResponse response = client.execute(httpPost);
            Calendar atr = Calendar.getInstance();
            logger.info("PNO response time: "+(atr.getTimeInMillis()-before));
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

            StringBuilder sb = new StringBuilder();

            String line;
            try {
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
                logger.error("IOException " + e);
            }

            logger.info(br.toString());

            return sb;

        } catch (ClientProtocolException e) {

            e.printStackTrace();
            logger.error("ClientProtocolException "+e.getMessage());

        } catch (IOException e) {

            e.printStackTrace();
            logger.error("IOException "+e.getMessage());
        }
        finally{
            try{
                if(br !=null)
                    br.close();
            }
            catch( IOException e){
                e.printStackTrace();
                logger.error("IOException "+e.getMessage());
            }

        }
        return null;
    }
}
