package com.example;
import com.example.node.*;

public class Test {
    public static void main(String[] args) throws Exception {
        TerminalOutputNode node1 = new TerminalOutputNode("node1");
        Wire wire1 = new Wire();
        node1.wire(wire1);
        Wire wire0 = new Wire();
        node1.wire(wire0);
        node1.start();

        EchoServerNode node2 = new EchoServerNode("node2");
        node2.wire(wire1);
        node2.start();

        TerminalInputNode node0 = new TerminalInputNode("node0");
        node0.wire(wire0);
        node0.start();

    }
}