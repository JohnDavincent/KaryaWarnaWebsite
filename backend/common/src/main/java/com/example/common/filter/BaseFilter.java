package com.example.common.filter;

import lombok.Data;

@Data
public class BaseFilter {
    private Integer page = 0;
    private Integer size = 5;
    private String orderBy = "Id";
    private String sortDir = "ASC";
}
