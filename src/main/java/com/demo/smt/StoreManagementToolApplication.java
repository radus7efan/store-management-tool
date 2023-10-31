package com.demo.smt;

import com.demo.smt.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {AppConfig.class})
public class StoreManagementToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreManagementToolApplication.class, args);
    }

}
