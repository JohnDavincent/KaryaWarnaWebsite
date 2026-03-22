package com.example.productservices.service;

import com.example.common.exception.SupplierExistException;
import com.example.common.exception.SupplierNotExistException;
import com.example.productservices.dto.SupplierCreateRequest;
import com.example.productservices.dto.SupplierResponse;
import com.example.productservices.dto.SupplierUpdateRequest;
import com.example.productservices.entity.Supplier;
import com.example.productservices.mapper.ProductMapper;
import com.example.productservices.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImp implements SupplierService{

    private final SupplierRepository supplierRepository;
    private final ProductMapper productMapper;

    @Override
    public SupplierResponse addSupplier(SupplierCreateRequest request) {
        if(supplierRepository.existsByName(request.getSupplierName())){
            throw new SupplierExistException("Supplier dengan nama" + request.getSupplierName() + "Sudah ada");
        }
        Supplier createSupplier = productMapper.mapToSupplier(request);
        supplierRepository.save(createSupplier);

        return productMapper.mapToSupplierResponse(createSupplier);
    }

    @Transactional
    @Override
    public SupplierResponse updateSupplier(Long supplierId, SupplierUpdateRequest request) {
        if(request == null){
            throw new RuntimeException("Input is invalid");
        }
        Supplier existSupplier = supplierRepository.findById(supplierId).orElseThrow(() -> new SupplierNotExistException("Supplier with name " + request.getSupplierName() + "doesn't exist"));
        if(request.getSupplierName() != null){
            existSupplier.setName(request.getSupplierName());
        }

        if(request.getContactPerson() != null){
            existSupplier.setPhoneNumber(request.getPhoneNumber());
        }

        if(request.getContactPerson() != null){
            existSupplier.setContactName(request.getContactPerson());
        }

        if(request.getDesc() != null){
            existSupplier.setDesc(request.getDesc());
        }

        supplierRepository.save(existSupplier);
        return productMapper.mapToSupplierResponse(existSupplier);
    }

    @Override
    public void deleteSupplier(Long id) {
       if(!supplierRepository.existsById(id)){
           throw new SupplierNotExistException("Supplier with id : " + id + "Not found");
       }
       supplierRepository.deleteById(id);
    }
}
