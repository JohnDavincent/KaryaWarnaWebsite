package com.example.transaction_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.example.transaction_services.client","com.example.common"})
public class TransactionServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServicesApplication.class, args);
	}

}
