package com.example.productservices.controller;

import com.example.common.dto.WebResponse;
import com.example.productservices.dto.SupplierRequest;
import com.example.productservices.dto.SupplierResponse;
import com.example.productservices.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/karyawarna")
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping("/create/supplier")
    public ResponseEntity<WebResponse<SupplierResponse>> addSupplier(@RequestBody SupplierRequest request){
        SupplierResponse createSupplier = supplierService.addSupplier(request);
        WebResponse<SupplierResponse>  response = WebResponse.<SupplierResponse>builder()
                    .status(HttpStatus.CREATED.value())
                    .message("berhasil Menambahkan supplier")
                    .code("CREATED")
                    .data(createSupplier)
                    .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);



    }


}
