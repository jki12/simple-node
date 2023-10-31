package com.example.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JComponent;


public class Node extends JButton {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 160;

    private static Node selectedNode; // 현재 선택 되어 있는 노드.

    private final UUID id;

    public Node(String type) {
        super(type);
        Node cur = this;

        id = UUID.randomUUID();

        setSize(WIDTH, HEIGHT);

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                
                if (selectedNode == null) {
                    selectedNode = cur;
                    System.out.println("ready to connect");
                }
                else if (selectedNode == cur) { // 만약 같은 노드 클릭하면 reset 된다.
                    System.out.println("");
                    selectedNode = null;
                }
                else if (selectedNode != cur) { // TODO pairing, impl can pair func.
                    String f = String.format("connect {%s} - {%s}", cur.id, selectedNode.id);

                    System.out.println(f);
                    selectedNode = null;
                }
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
