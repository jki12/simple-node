package com.example.node;

import com.example.Wire;

import lombok.Getter;

@Getter
public abstract class WirableNode extends ActiveNode {
    private static final int SIZE = 10;
    
    private Wire[] wires = new Wire[SIZE];
    
    protected WirableNode(String name) {
        super(name);
    }

    public void wire() {
        // TODO wire
    }
}
