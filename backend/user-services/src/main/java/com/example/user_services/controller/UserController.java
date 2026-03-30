package com.example.user_services.controller;

import com.example.user_services.dto.ProfileResponse;
import com.example.user_services.dto.UserRegisterRequest;
import com.example.user_services.dto.UserRegisterResponse;
import com.example.user_services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/karyawarna")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    ResponseEntity<UserRegisterResponse> createUser(@RequestBody UserRegisterRequest request){
        UserRegisterResponse createUser = userService.userRegister(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    @GetMapping("/profile")
    //@CurrentSecurityContext --> if user input the api above but havent login, the security gonna throw excepation
    public ProfileResponse getProfile(@CurrentSecurityContext(expression = "authentication?.name") String email){
        return userService.getProfile(email);
    }

}
