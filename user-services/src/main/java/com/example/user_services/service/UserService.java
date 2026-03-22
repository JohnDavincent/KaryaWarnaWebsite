package com.example.user_services.service;

import com.example.user_services.dto.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    public UserRegisterResponse userRegister(UserRegisterRequest request);
    public ProfileResponse getProfile(String email);
}

