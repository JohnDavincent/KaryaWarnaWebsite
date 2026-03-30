package com.example.user_services.filter;



import com.example.user_services.service.AppUserDetailService;
import com.example.user_services.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilterRequest extends OncePerRequestFilter {

    private final AppUserDetailService appUserDetailService;
    private final JwtUtil jwtUtil;

    private static final List<String> PUBLIC_URL = List.of("/karyawarna/login","/karyawarna/register","/karyawarna/send-request-otp","/karyawarna/reset-password","logout");

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            // serrvletPath --> just take the link ex: http:lcalhost8080:/api/login --> /api/login
            String path = request.getServletPath();
            System.out.println("=== JWT FILTER ===");
            System.out.println("Path: " + path);
            // if there is contain the path in the above
            if(PUBLIC_URL.contains(path)){
                System.out.println("Public URL - skipping auth");
                filterChain.doFilter(request,response);
                return; // return it
            }

            String jwt = null; // set to null
            String email = null; // set to null

            //1. check the authorization header
            final String authorizationHeader = request.getHeader("Authorization");
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                jwt = authorizationHeader.substring(7);
                System.out.println("JWT from header: found");
            }

            //2. if it is not found in header, check cookies

            if(jwt == null){
                Cookie[] cookies = request.getCookies();
                System.out.println("Cookies null? " + (cookies == null));
                if(cookies != null){
                    for( Cookie cookie : cookies){
                        System.out.println("Cookie: " + cookie.getName());
                        if("jwt".equals(cookie.getName())){
                            System.out.println("JWT from cookie: found");
                            jwt = cookie.getValue();
                            break;
                        }
                    }
                }

            }

            System.out.println("JWT found? " + (jwt != null));
            //3. validate the tokens and security context
            if(jwt != null){
                try {
                    email = jwtUtil.extractEmail(jwt);
                    System.out.println("Email extracted: " + email);

                    if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                        UserDetails userDetail = appUserDetailService.loadUserByUsername(email);
                        System.out.println("User found: " + userDetail.getUsername());

                        boolean isValid = jwtUtil.validateToken(jwt, userDetail);
                        System.out.println("Token valid? " + isValid);
                        if(isValid){
                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));SecurityContextHolder.getContext().setAuthentication(authToken);
                        }

                    }
                } catch(Exception e) {
                    System.out.println("JWT ERROR: " + e.getMessage());
                    e.printStackTrace();
                }
    ;        }
            filterChain.doFilter(request,response);

        }
}
