package com.example.productservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {"com.example.productservices","com.example.common"})
@EnableFeignClients
public class ProductServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServicesApplication.class, args);
    }

}
