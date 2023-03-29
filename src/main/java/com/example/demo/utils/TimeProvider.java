package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TimeProvider {

    public LocalDate getLocalDate(){
        return LocalDate.now();
    }

}
