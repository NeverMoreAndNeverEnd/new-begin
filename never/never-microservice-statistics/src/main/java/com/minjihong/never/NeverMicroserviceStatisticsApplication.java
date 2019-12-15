package com.minjihong.never;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NeverMicroserviceStatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeverMicroserviceStatisticsApplication.class, args);
    }

}
