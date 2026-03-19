package com.example.productservices.controller;

import com.example.common.dto.WebResponse;
import com.example.productservices.dto.SupplierCreateRequest;
import com.example.productservices.dto.SupplierResponse;
import com.example.productservices.dto.SupplierUpdateRequest;
import com.example.productservices.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/karyawarna/admin")
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping("/create/supplier")
    public ResponseEntity<WebResponse<SupplierResponse>> addSupplier(@Valid @RequestBody SupplierCreateRequest request){
        SupplierResponse createSupplier = supplierService.addSupplier(request);
        WebResponse<SupplierResponse>  response = WebResponse.<SupplierResponse>builder()
                    .status(HttpStatus.CREATED.value())
                    .message("berhasil Menambahkan supplier")
                    .code("CREATED")
                    .data(createSupplier)
                    .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PatchMapping("/update/supplier/{id}")
    ResponseEntity<WebResponse<SupplierResponse>> updateSupplier(@Valid @PathVariable Long id, @RequestBody SupplierUpdateRequest request){
        SupplierResponse supplierResponse = supplierService.updateSupplier(id, request);
        WebResponse<SupplierResponse> response = WebResponse.<SupplierResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Successfully update supplier data for id : " + id)
                .code("OK")
                .data(supplierResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/supplier/{id}")
    ResponseEntity<WebResponse<String>> deleteSupplier(@PathVariable Long id){
        supplierService.deleteSupplier(id);
        WebResponse<String> response = WebResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil menghapus supplier")
                .code("OK")
                .data(null)
                .build();

        return ResponseEntity.ok(response);
    }


}
