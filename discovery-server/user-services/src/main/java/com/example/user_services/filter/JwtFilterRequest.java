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

    private static final List<String> PUBLIC_URL = List.of("/api/login","/api/register","/api/send-request-otp","/api/reset-password","logout");

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            // serrvletPath --> just take the link ex: http:lcalhost8080:/api/login --> /api/login
            String path = request.getServletPath();
            // if there is contain the path in the above
            if(PUBLIC_URL.contains(path)){
                filterChain.doFilter(request,response);
                return; // return it
            }

            String jwt = null; // set to null
            String email = null; // set to null

            //1. check the authorization header
            final String authorizationHeader = request.getHeader("Authorization");
            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
                jwt = authorizationHeader.substring(7);
            }

            //2. if it is not found in header, check cookies
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for( Cookie cookie : cookies){
                    if("jwt".equals(cookie.getName())){
                        jwt = cookie.getValue();
                        break;
                    }
                }
            }

            //3. validate the tokens and security context
            if(jwt != null){
                email = jwtUtil.extractEmail(jwt);
                if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetail = appUserDetailService.loadUserByUsername(email);

                    if(jwtUtil.validateToken(jwt, userDetail)){
                        // using null because we know the user is already login so no need to save the password
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());

                        //WebAuthenticationDetailsSource --> add some meta data to the authenticationToken --> ip Address and Session Id;
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        //SecurityContextHolder --> this one that give permission
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
    ;        }
            filterChain.doFilter(request,response);

        }
}
