package com.example.common.dto;


import lombok.Builder;
import java.io.Serializable;

@Builder
public record WebResponse <T>(
    Integer status,
    String message,
    String code,
    T data
)implements Serializable {}
