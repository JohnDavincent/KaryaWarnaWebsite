package com.example.transaction_services.client;

import com.example.transaction_services.dto.OrderDetailProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.redis.core.script.ReactiveScriptExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(url = "http://localhost:8101/karyawarna/admin/")

public interface ProductClient {

    @PostMapping("/get/product/{id}")
    ResponseEntity<OrderDetailProduct> getProductDetails(@PathVariable UUID id);
}
