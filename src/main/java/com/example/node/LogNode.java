package com.example.node;

import com.example.Wire;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogNode extends WirableNode {
    Wire[] wires;

    protected LogNode(String name, int count) {
        super(name);
        wires = new Wire[count];
    }

    @Override
    public boolean canWire(WirableNode other) {
        return false;
    }

    @Override
    public void preprocess() {
    }

    @Override
    public void process() {
        for(int i = 0; i < wires.length; i++) {
            if (wires[i] != null && wires[i].hasMessage()) {
                log.trace(wires[i].getMessage().toString());
            }
        }
    }

    @Override
    public void postprocess() {
    }
    
}
