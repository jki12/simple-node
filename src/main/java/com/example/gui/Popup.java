package com.example.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import lombok.Getter;

@Getter
public class Popup extends JPanel {
    private final JTextField portField;
    private final JTextField hostField;

    public Popup() {
        JLabel port = new JLabel("port");
        JLabel host = new JLabel("host");

        add(port);
        this.portField = new JTextField(7);
        add(portField);

        add(host);
        this.hostField = new JTextField(10);
        hostField.setText("localhost");
        add(hostField);
    }
}
