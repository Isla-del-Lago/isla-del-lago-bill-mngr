package com.isladellago.billmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BillmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillmanagerApplication.class, args);
    }

}
