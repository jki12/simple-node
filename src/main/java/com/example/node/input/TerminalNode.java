package com.example.node.input;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.example.message.Message;
import com.example.message.TextMessage;

public class TerminalNode extends InputNode {
    private BufferedReader br;

    public TerminalNode(String name, int count) {
        super(name, count);
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

            output(msg);
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
