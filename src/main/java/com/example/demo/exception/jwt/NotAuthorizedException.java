package com.example.demo.exception.jwt;


public class NotAuthorizedException extends RuntimeException{

    public NotAuthorizedException(String message){
        super(message);
    }

}
