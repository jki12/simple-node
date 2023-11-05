package com.example.node;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.example.message.Message;
import com.example.message.TextMessage;

public class TerminalInputNode extends InputNode {
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
            String line = br.readLine();
            Message msg = new TextMessage(line);

            // TODO output(msg);
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
}
