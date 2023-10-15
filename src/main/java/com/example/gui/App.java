package com.example.gui;

import javax.swing.JButton;
import javax.swing.JFrame;

public class App {
    private static final String TITLE = "tester";

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JFrame jFrame;

    public App() {
        this(WIDTH, HEIGHT);
    }

    public App(final int width, final int height) {
        jFrame = new JFrame(TITLE);

        jFrame.add(new JButton("hi"));

        jFrame.setSize(width, height);
        jFrame.setVisible(true);
    }
}
