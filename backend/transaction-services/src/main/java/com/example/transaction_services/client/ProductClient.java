package com.example.transaction_services.client;

import com.example.common.dto.WebResponse;
import com.example.transaction_services.dto.OrderDetailProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.UUID;

@FeignClient(
        name = "product-services",
        url = "http://localhost:8101/karyawarna/admin/"
)

public interface ProductClient {

    @PostMapping("/get/product/{id}")
    ResponseEntity<OrderDetailProduct> getProductDetails(@PathVariable UUID id);

    @PostMapping("/update-stock")
    void updateStock(@RequestBody Map<UUID, Integer>map);
}

