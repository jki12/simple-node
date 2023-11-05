package com.example.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;

import javax.swing.JButton;

public class NodeBlock extends JButton {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 160;

    private static NodeBlock selectedNode;

    private final UUID id;

    public NodeBlock(String type) {
        super(type);
        NodeBlock cur = this;

        id = UUID.randomUUID();

        setSize(WIDTH, HEIGHT);

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                
                if (selectedNode == null) {
                    selectedNode = cur;
                    setForeground(Color.BLUE);
                    System.out.println("ready to connect");
                }
                else if (selectedNode == cur) { // 만약 같은 노드 클릭하면 unselect 된다.
                    selectedNode = null;
                }
                else if (selectedNode != cur) { // TODO pairing, impl can pair func.
                    // String f = String.format("connect {%s} - {%s}", cur.id, selectedNode.id);

                    // System.out.println(f);
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
