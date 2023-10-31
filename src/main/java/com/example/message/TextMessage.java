package com.example.message;

import lombok.Getter;

@Getter
public class TextMessage extends Message {
    private String text;

    public TextMessage(String text) {
        this.text = text;
    }
}
