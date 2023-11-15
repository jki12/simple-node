package com.example.node;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.example.Wire;
import com.example.message.Message;

public class TerminalOutputNode extends WirableNode implements OutputNode {
    public TerminalOutputNode(String name) {
        super(name);
    }

    @Override
    public void process() {
        for (var wire : wires) {
            if (wire.hasMessage()) {
                Message msg = wire.getMessage();

                System.out.println(msg.toString());
            }
        }
    }

    @Override
    public void preprocess() {
    }

    @Override
    public void postprocess() {
    }

    @Override
    public void wireIn(Wire wire) {
        wires.add(wire);
    }

    @Override
    public boolean canWire(WirableNode other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canWire'");
    }
}
