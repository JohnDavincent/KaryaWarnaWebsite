package com.example.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


public record WebResponse <T>(
    Integer status,
    String message,
    String code,
    T data
)implements Serializable {}
