package com.example;

import java.io.IOException;

import com.example.gui.App;
import com.example.node.EchoServerNode;
import com.example.node.ServerNode;
import com.example.node.TerminalInputNode;
import com.example.node.TerminalOutputNode;

public class Main {
    public static void main(String[] args) throws IOException {
        // {
        //     // test.

            
        //     TerminalInputNode a = new TerminalInputNode("a");
        //     TerminalOutputNode b = new TerminalOutputNode("b");

            
        //     // ServerNode c = new ServerNode("c");
        //     EchoServerNode c = new EchoServerNode("c");

        //     Wire wire = new Wire();

        //     a.wire(wire);
        //     b.wire(wire);
        //     c.wire(wire);

        //     a.start();
        //     b.start();
        //     c.start();
        // }

        new App();        
    }
}