package com.example.user_services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserRegisterResponse {
    private String user_id;
    private String name;
    private String email;
    private Boolean isAccountVerified;
}
