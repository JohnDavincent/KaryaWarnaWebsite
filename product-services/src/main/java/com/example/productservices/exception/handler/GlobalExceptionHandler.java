package com.example.productservices.exception.handler;

import com.example.productservices.exception.CategoryExistException;
import com.example.productservices.exception.ProductExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductExistException.class)
    public ExceptionResponse handleProductExist(ProductExistException e){
        log.error("Product already exist in database : {} ",e.getMessage());
        return new ExceptionResponse(404,e.getMessage());
    }

    @ExceptionHandler(CategoryExistException.class)
    public ExceptionResponse handleCategoryExist(CategoryExistException e){
        log.error("Category already Exist!");
        return new ExceptionResponse(409,e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ExceptionResponse handleDatabaseIntegrityViolation(DataIntegrityViolationException e){
        log.error("Error data conflict : {}", e.getMessage());
        return new ExceptionResponse(409, e.getMessage());
    }
}
