package com.example.node;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public abstract class Node {
    private static int count;

    protected final UUID id;
    protected final OffsetDateTime createdDate;
    @Setter
    protected String name;

    protected Node(String name) {
        count++;

        createdDate = OffsetDateTime.now();
        id = UUID.randomUUID();
        this.name = name;

        log.info("created node {}:{}", name, id);
    }

    public static int getCount() {
        return count;
    }
}
