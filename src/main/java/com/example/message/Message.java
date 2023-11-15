package com.example.message;

import org.json.JSONObject;

public class Message extends JSONObject {
    private static final ContentType DEFAULT_CONTENT_TYPE = ContentType.PLAIN_TEXT;

    public Message() {
        setMessage("");
        this.put("contentType", DEFAULT_CONTENT_TYPE);
    }

    public void setMessage(String message) {
        this.put("message", message);
    }

    // TODO 지원하는 타입 enum 만들기.
}
