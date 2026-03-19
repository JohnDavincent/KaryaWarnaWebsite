package com.example.common.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Optional;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            try {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes == null) {
                    return Optional.of("SYSTEM");
                }
                HttpServletRequest request = attributes.getRequest();
                Cookie[] cookies = request.getCookies();
                if (cookies == null) {
                    return Optional.of("SYSTEM");
                }

                return Arrays.stream(cookies)
                        .filter(cookie -> cookie.getName().equals("jwt"))
                        .findFirst()
                        .map(cookie -> getUserNameFromToken(cookie.getValue()))
                        .or(() -> Optional.of("SYSTEM"));
            } catch (Exception e) {
                return Optional.of("SYSTEM");
            }
        };
    }

    public String getUserNameFromToken(String token){
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
