package com.example.user_services.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserLoginRequest {
    @NotBlank(message = "username tidak boleh kosong")
    private String email;

    @NotBlank
    @Size(min = 6, message = "Minimal 6 karakter")
    private String password;
}
