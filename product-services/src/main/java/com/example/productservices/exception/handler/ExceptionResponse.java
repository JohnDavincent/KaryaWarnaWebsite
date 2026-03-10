package com.example.productservices.exception.handler;

public record ExceptionResponse(
        Integer status,
        String message
) {
}
