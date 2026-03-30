package com.example.common.handler;

import com.example.common.dto.WebResponse;
import com.example.common.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductExistException.class)
    public ResponseEntity<WebResponse<ProductExistException>> handleProductExist(ProductExistException e){
        log.error("Product already exist in database : {} ",e.getMessage());
        WebResponse<ProductExistException> response = WebResponse.<ProductExistException>builder()
                    .status(HttpStatus.CONFLICT.value())
                    .message(e.getMessage())
                    .code("CONFLICT")
                    .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(CategoryExistException.class)
    public ResponseEntity<WebResponse<CategoryExistException>> handleCategoryExist(CategoryExistException e){
        log.error("Category already Exist!");
        WebResponse<CategoryExistException> response = WebResponse.<CategoryExistException>builder()
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .code("CONFLICT")
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<WebResponse<DataIntegrityViolationException>> handleDatabaseIntegrityViolation(DataIntegrityViolationException e){
        log.error("Error data conflict : {}", e.getMessage());
        WebResponse<DataIntegrityViolationException> response = WebResponse.<DataIntegrityViolationException>builder()
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .code("CONFLICT")
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(CategoryNotExistException.class)
    public ResponseEntity<WebResponse<CategoryNotExistException>> handleCategoryNotExistViolation(CategoryNotExistException e){
        log.error("category not found : {}",e.getMessage());
        WebResponse<CategoryNotExistException> response = WebResponse.<CategoryNotExistException>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .code("BAD REQUEST")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(SupplierExistException.class)
    public ResponseEntity<WebResponse<SupplierExistException>> handleDuplicateSupplier(SupplierExistException e){
        log.error("Supplier already exist : {} ", e.getMessage());
        WebResponse<SupplierExistException> response = WebResponse.<SupplierExistException>builder()
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .code("CONFLICT")
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<WebResponse<ProductNotExistException>> handleProductNotExist(ProductNotExistException e){
        log.error("Product Not found : {} ", e.getMessage());
        WebResponse<ProductNotExistException> response = WebResponse.<ProductNotExistException>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .code("BAD REQUEST")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BrandAlreadyExistException.class)
    public ResponseEntity<WebResponse<BrandAlreadyExistException>> handleBrandAlreadyExist(BrandAlreadyExistException e){
        log.error("Brand already exist : {} ", e.getMessage());
        WebResponse<BrandAlreadyExistException> response = WebResponse.<BrandAlreadyExistException>builder()
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .code("CONFLICT")
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(BrandNotExistException.class)
    public ResponseEntity<WebResponse<BrandNotExistException>> handleBrandNotExist(BrandNotExistException e){
        log.error("Brand is not exist : {} ", e.getMessage());
        WebResponse<BrandNotExistException> response = WebResponse.<BrandNotExistException>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .code("BAD REQUEST")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(PrefixAlreadyExistException.class)
    public ResponseEntity<WebResponse<PrefixAlreadyExistException>> handlePrefixExist(PrefixAlreadyExistException e){
        log.error("Prefix already exist : {} ", e.getMessage());
        WebResponse<PrefixAlreadyExistException> response = WebResponse.<PrefixAlreadyExistException>builder()
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .code("CONFLICT")
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(OrderDetailExistException.class)
    public ResponseEntity<WebResponse<OrderDetailExistException>> handleOrderDetailExist(OrderDetailExistException e){
        log.error("Order Detail already exist");
        WebResponse<OrderDetailExistException> response = WebResponse.<OrderDetailExistException>builder()
                .status(HttpStatus.CONFLICT.value())
                .code("CONFLICT")
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(OrderNotExistException.class)
    public ResponseEntity<WebResponse<OrderNotExistException>> handleOrderNotExist(OrderNotExistException e){
        log.error("Order is not exist");
        WebResponse<OrderNotExistException> response = WebResponse.<OrderNotExistException>builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .code("BAD REQUEST")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }



}
