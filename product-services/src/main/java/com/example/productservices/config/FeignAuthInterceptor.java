package com.example.productservices.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;


public class FeignAuthInterceptor implements RequestInterceptor {

    private static final String JWT_TOKEN = "jwt";


    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if(attributes != null){
            HttpServletRequest request = attributes.getRequest();
            Cookie[] cookies = request.getCookies();
            Arrays.stream(cookies)
                    .filter(cookie -> JWT_TOKEN.equals(cookie.getName()))
                    .findFirst()
                    .ifPresent(jwtCookie -> {
                        String jwtToken = jwtCookie.getValue();
                        requestTemplate.header(JWT_TOKEN, jwtCookie.getName() +"="+ jwtToken);
                    });
        }
    }
}
