package com.example.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;


public class Node extends JButton {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 160;

    public Node(String type) {
        super(type);

        setSize(WIDTH, HEIGHT);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Connect plz");
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int newX = e.getX() + getX();
                int newY = e.getY() + getY();

                setBounds(newX, newY, WIDTH / 2, HEIGHT / 2);
            }
        });
    }
}
