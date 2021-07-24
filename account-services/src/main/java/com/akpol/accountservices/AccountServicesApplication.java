package com.akpol.accountservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan(basePackages = "com.akpol.commons.model")
@EnableEurekaClient
public class AccountServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServicesApplication.class, args);
    }

}
