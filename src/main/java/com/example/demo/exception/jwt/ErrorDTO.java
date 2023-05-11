package com.example.demo.exception.jwt;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class ErrorDTO {

    private String code;
    private Timestamp timestamp;
    private String message;
}
