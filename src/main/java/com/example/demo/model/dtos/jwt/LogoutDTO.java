package com.example.demo.model.dtos.jwt;

import lombok.Value;

@Value
public class LogoutDTO {

    private String accessToken;
    private String refreshToken;

}
