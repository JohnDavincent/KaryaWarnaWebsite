package com.example.common.handler;

import com.example.common.dto.WebResponse;
import com.example.common.exception.CategoryExistException;
import com.example.common.exception.CategoryNotExistException;
import com.example.common.exception.ProductExistException;
import com.example.common.exception.SupplierExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductExistException.class)
    public WebResponse<ProductExistException> handleProductExist(ProductExistException e){
        log.error("Product already exist in database : {} ",e.getMessage());
        return WebResponse.<ProductExistException>builder()
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .code("CONFLICT")
                .build();
    }

    @ExceptionHandler(CategoryExistException.class)
    public WebResponse<CategoryExistException> handleCategoryExist(CategoryExistException e){
        log.error("Category already Exist!");
        return WebResponse.<CategoryExistException>builder()
                .status(HttpStatus.CONFLICT.value())
                .code("CONFLICT")
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public WebResponse<DataIntegrityViolationException> handleDatabaseIntegrityViolation(DataIntegrityViolationException e){
        log.error("Error data conflict : {}", e.getMessage());
        return WebResponse. <DataIntegrityViolationException>builder()
                .status(HttpStatus.CONFLICT.value())
                .code("CONFLICT")
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(CategoryNotExistException.class)
    public WebResponse<CategoryNotExistException> handleCategoryNotExistViolation(CategoryNotExistException e){
        log.error("category not found : {}",e.getMessage());
        return WebResponse.<CategoryNotExistException>builder()
                .status(HttpStatus.CONFLICT.value())
                .code("CONFLICT")
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(SupplierExistException.class)
    public WebResponse<SupplierExistException> handleDuplicateSupplier(SupplierExistException e){
        log.error("Supplier already exist : {} ", e.getMessage());
        return WebResponse.<SupplierExistException>builder()
                .status(HttpStatus.CONFLICT.value())
                .code("CONFLICT")
                .message(e.getMessage())
                .build();
    }


}
