package com.example.productservices.service;

import com.example.common.exception.SupplierExistException;
import com.example.productservices.dto.SupplierRequest;
import com.example.productservices.dto.SupplierResponse;
import com.example.productservices.entity.Supplier;
import com.example.productservices.mapper.ProductMapper;
import com.example.productservices.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImp implements SupplierService{

    private final SupplierRepository supplierRepository;
    private final ProductMapper productMapper;

    @Override
    public SupplierResponse addSupplier(SupplierRequest request) {
        if(supplierRepository.existsByName(request.getSupplierName())){
            throw new SupplierExistException("Supplier dengan nama" + request.getSupplierName() + "Sudah ada");
        }
        Supplier createSupplier = productMapper.mapToSupplier(request);
        supplierRepository.save(createSupplier);

        return productMapper.mapToSupplierResponse(createSupplier);
    }
}
