package com.example.user_services.service;

import com.example.user_services.entity.UserEntity;
import com.example.user_services.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity existUser = userRepository.findByEmail(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"user not found"));

        //tell the spring this user authorities
        List<SimpleGrantedAuthority> authorities = existUser.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getType())).toList();

        return new User(existUser.getEmail(), existUser.getPassword(), authorities);
    }
}
