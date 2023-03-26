package com.example.demo.exception;

public class AddressNotFoundException extends RuntimeException{

    public AddressNotFoundException() {
        super("Address not found");
    }

}
