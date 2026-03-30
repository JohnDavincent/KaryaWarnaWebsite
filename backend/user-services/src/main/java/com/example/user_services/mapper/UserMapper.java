package com.example.user_services.mapper;

import com.example.user_services.dto.ProfileResponse;
import com.example.user_services.dto.UserRegisterRequest;
import com.example.user_services.dto.UserRegisterResponse;
import com.example.user_services.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserEntity mapToUser(UserRegisterRequest request){
        return UserEntity.builder()
                .name(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .user_id(UUID.randomUUID().toString())
                .isAccountVerified(false)
                .resetOtp(null)
                .verifyOtp(null)
                .resetOtpExpiredAt(0L)
                .verifyOtpExpiredAt(0L)
                .build();
    }


    public UserRegisterResponse mapToUserResponse(UserEntity userEntity){
        return UserRegisterResponse.builder()
                .name(userEntity.getName())
                .user_id(userEntity.getUser_id())
                .email(userEntity.getEmail())
                .isAccountVerified(false)
                .build();
    }

    public ProfileResponse mapToUserProfile(UserEntity userEntity){
        return ProfileResponse.builder()
                .username(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }
}
