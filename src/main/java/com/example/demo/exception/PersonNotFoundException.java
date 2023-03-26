package com.example.demo.exception;

public class PersonNotFoundException extends RuntimeException{

    public PersonNotFoundException() {
        super("Person not found");
    }

}
