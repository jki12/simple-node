package com.example.node;

public abstract class InputNode extends WirableNode {
    // protected final Wire[] wires;

    protected InputNode(String name) {
        super(name);

        // if (0 >= count) throw new IllegalArgumentException();

        // wires = new Wire[count];
    }

    // public void connect(Wire wire, int index) {
    //     wires[index] = wire;
    // }

    // public void output(Message message) {
    //     for (var wire : wires) wire.put(message);
    // }
}
