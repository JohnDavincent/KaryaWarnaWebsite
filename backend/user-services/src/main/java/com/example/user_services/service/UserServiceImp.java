package com.example.user_services.service;

import com.example.user_services.dto.*;
import com.example.user_services.entity.RoleEntity;
import com.example.user_services.entity.UserEntity;
import com.example.user_services.mapper.UserMapper;
import com.example.user_services.repository.RoleRepository;
import com.example.user_services.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public UserRegisterResponse userRegister(UserRegisterRequest request) {
        UserEntity newUser = userMapper.mapToUser(request);
        if(userRepository.existsByEmail(newUser.getEmail())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"email already exist");
        }
        RoleEntity roleUser = roleRepository.findByType("ROLE_USER").orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found"));
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleUser);
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return userMapper.mapToUserResponse(newUser);
    }

    @Override
    public ProfileResponse getProfile(String email) {
        UserEntity existingUser = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found" + email));
        return userMapper.mapToUserProfile(existingUser);
    }

}


