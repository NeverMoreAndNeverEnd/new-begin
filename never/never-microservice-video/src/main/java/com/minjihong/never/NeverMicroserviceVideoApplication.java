package com.minjihong.never;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NeverMicroserviceVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeverMicroserviceVideoApplication.class, args);
    }

}
