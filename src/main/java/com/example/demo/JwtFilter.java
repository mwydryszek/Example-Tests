package com.example.demo;

import com.example.demo.service.JwtService;
import liquibase.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String authHeaderValue = request.getHeader(AUTHORIZATION_HEADER);

        if(!StringUtils.isEmpty(authHeaderValue)
                && authHeaderValue.startsWith(BEARER_TOKEN_PREFIX)){
           String token = extractBareToken(authHeaderValue);
           String username = jwtService.getUsernameFromAccessToken(token);
        }


    }

    private String extractBareToken(String authHeaderValue) {
        return authHeaderValue.substring(7);
    }
}
