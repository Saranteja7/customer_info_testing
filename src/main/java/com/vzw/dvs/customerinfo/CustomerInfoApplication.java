package com.vzw.dvs.customerinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class CustomerInfoApplication
{
    public static void main( String[] args )
    {
    	SpringApplication.run(CustomerInfoApplication.class, args);

    }
}