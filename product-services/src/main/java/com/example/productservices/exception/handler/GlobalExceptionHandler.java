package com.example.productservices.exception.handler;

import com.example.productservices.exception.ProductExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductExistException.class)
    public ExceptionResponse ProductExist(ProductExistException e){
        log.error("Product already exist in database : {} ",e.getMessage());
        return new ExceptionResponse(404,e.getMessage());
    }

}
