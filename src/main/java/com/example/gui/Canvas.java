package com.example.gui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.example.node.Node;
import com.example.node.WirableNode;

public class Canvas extends JPanel {
    private ArrayList<NodeBlock> nodes = new ArrayList<>(); // 노드들, 여기서 연결 되어 있는지 확인하고 연결한다.

    public Canvas() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void add(NodeBlock node) {
        nodes.add(node);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < nodes.size(); ++i) {
            var block1 = nodes.get(i);

            if (!(block1.getNode() instanceof WirableNode)) continue;
            WirableNode w1 = (WirableNode) block1.getNode();

            for (int j = i + 1; j < nodes.size(); ++j) {
                var block2 = nodes.get(j);

                if (!(block2.getNode() instanceof WirableNode)) continue;
                WirableNode w2 = (WirableNode) block2.getNode();

                if (WirableNode.isWire(w1, w2)) {
                    int x1 = (block1.getX() < block2.getX() ? block1.getX() + block1.getWidth() : block1.getX());
                    int x2 = (block2.getX() < block1.getX() ? block2.getX() + block2.getWidth() : block2.getX());

                    int y1 = (block1.getY() < block2.getY() ? block1.getY() + block1.getHeight() : block1.getY());
                    int y2 = (block2.getY() < block1.getY() ? block2.getY() + block2.getHeight() : block2.getY());

                    g.drawLine(x1, y1, x2, y2);
                }
            }
        }
    }
}
