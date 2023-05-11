package com.example.demo.service;


import com.example.demo.model.dtos.jwt.AuthenticationDTO;
import com.example.demo.model.dtos.jwt.LogoutDTO;
import com.example.demo.model.dtos.jwt.UsernamePasswordDTO;

public interface JwtService {
    AuthenticationDTO auth(UsernamePasswordDTO usernamePasswordDTO);

    void logout(LogoutDTO logoutDTO);
}
