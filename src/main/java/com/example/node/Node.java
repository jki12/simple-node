package com.example.node;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Getter;

@Getter
public abstract class Node {
    private static int count;

    protected final UUID id;
    protected final OffsetDateTime createdDate;
    protected String name;

    protected Node(String name) {
        count++;

        createdDate = OffsetDateTime.now();
        id = UUID.randomUUID();
        this.name = name;

        // log.trace("create node : {}", id);
    }

    public void setName(String name) {
        this.name = name;
    }
}
