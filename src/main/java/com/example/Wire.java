package com.example;

import java.util.LinkedList;
import java.util.Queue;

import com.example.message.Message;

public class Wire {
    Queue<Message> q = new LinkedList<>();

    public void put(Message message) {
        q.add(message);
    }

    public boolean hasMessage() {
        return (!q.isEmpty());
    }

    public Message get() {
        return q.poll();
    }
}