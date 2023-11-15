package com.example.node;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.example.Wire;
import com.example.message.Message;

public class TerminalInputNode extends WirableNode implements InputNode {
    private BufferedReader br;

    public TerminalInputNode(String name) {
        super(name);
    }

    @Override
    public void preprocess() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void process() {
        try {
            Message msg = new Message();
            msg.put("message", br.readLine());

            for (var wire : wires) {
                wire.put(msg);
            }
            
        } catch (Exception ignore) {
        }
    }

    public void postprocess() {
        try {
            br.close();
            br = null;
        } catch (Exception ignroe) {
        }
    }

    @Override
    public void wireOut(Wire wire) {
        wires.add(wire);
    }

    @Override
    public boolean canWire(WirableNode other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canWire'");
    }
}
