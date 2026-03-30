package com.example.user_services.dto;

import jakarta.validation.constraints.Email;
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
public class UserRegisterRequest {
    @NotBlank
    @Size(min = 6, message = "Masukan kata minimal 6 karakter")
    private String username;

    @NotBlank
    @Size(min = 8, message = "Masukan kata minimal 8 karakter")
    private String password;

    @Email(message = "Masukkan email yang valid")
    @NotBlank
    private String email;
}
