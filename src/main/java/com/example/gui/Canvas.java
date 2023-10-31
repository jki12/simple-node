package com.example.gui;

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Canvas extends JPanel {
    public Canvas() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void paintComponent(Graphics g) { // test code.
        super.paintComponent(g);

        g.drawLine(10, 20, 20, 20);
    }
}
