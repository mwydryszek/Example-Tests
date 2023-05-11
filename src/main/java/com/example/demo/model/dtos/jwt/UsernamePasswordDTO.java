package com.example.demo.model.dtos.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsernamePasswordDTO {
    private String username;
    private String password;
}
