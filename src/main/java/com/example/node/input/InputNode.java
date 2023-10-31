package com.example.node.input;

import com.example.Wire;
import com.example.message.Message;
import com.example.node.ActiveNode;

public abstract class InputNode extends ActiveNode {
    protected final Wire[] wires;

    protected InputNode(String name, int count) {
        super(name);

        if (0 >= count) throw new IllegalArgumentException();

        wires = new Wire[count];
    }

    public void connect(Wire wire, int index) {
        wires[index] = wire;
    }

    public void output(Message message) {
        for (var wire : wires) wire.put(message);
    }
}
