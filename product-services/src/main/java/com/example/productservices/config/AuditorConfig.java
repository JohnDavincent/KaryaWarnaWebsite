package com.example.productservices.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.Key;
import java.util.Arrays;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditorConfig{

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Bean
    public AuditorAware<String> auditorProvider() {
        // Menggunakan Lambda Expression (Sangat Modern & Elegan!)
        return () -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                Cookie[] cookies = request.getCookies();

                if (cookies != null) {
                    return Arrays.stream(cookies)
                            .filter(cookie -> "jwt".equals(cookie.getName()))
                            .findFirst()
                            .map(cookie -> getUsernameFromToken(cookie.getValue()));
                }
            }
            return Optional.of("SYSTEM");
        };
    }

    public String getUsernameFromToken(String token){
        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        }catch (Exception e){
            return "ANONYMOUS";
        }
    }




}
