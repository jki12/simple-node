package com.example;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import com.example.message.Message;

public final class Wire {
    private static int count;

    private String name;
    private Queue<Message> q = new LinkedList<>();

    public Wire() {
        name = "wire" + count++;
    }

    public String getName() {
        return name;
    }

    public void put(Message message) {
        q.add(message);
    }

    public boolean hasMessage() {
        return (!q.isEmpty());
    }

    public Message getMessage() {
        return q.poll();
    }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(name);
    //     final int prime = 31;
    //     int result = 1;
    //     result = prime * result + ((name == null) ? 0 : name.hashCode());
    //     return result;
    // }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null)
    //         return false;
    //     if (getClass() != obj.getClass())
    //         return false;
    //     Wire other = (Wire) obj;
    //     if (name == null) {
    //         if (other.name != null)
    //             return false;
    //     } else if (!name.equals(other.name))
    //         return false;
    //     return true;
    // }

    
}