package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageCollector {

    private final List<String> messages;

    public MessageCollector() {
        this.messages = new ArrayList<>();
    }

    public void addError(String message){
        this.messages.add(message);
    }
    public boolean hasErrors(){
        return messages.size() > 0;
    }

    public String buildMessage(){
        return String.join("\n", messages);
    }

}
