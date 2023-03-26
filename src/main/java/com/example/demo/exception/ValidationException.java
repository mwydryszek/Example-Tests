package com.example.demo.exception;

import com.example.demo.utils.MessageCollector;

public class ValidationException extends RuntimeException{

    public ValidationException(MessageCollector messageCollector) {
        super(messageCollector.buildMessage());
    }
}
