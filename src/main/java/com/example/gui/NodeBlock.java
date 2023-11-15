package com.example.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.Wire;
import com.example.node.Node;
import com.example.node.WirableNode;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class NodeBlock extends JButton {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 160;

    private static NodeBlock selectedNode;

    private final UUID id;
    private final Node node;

    private static Canvas canvas;

    public static void set(Canvas canvas) {
        NodeBlock.canvas = canvas;
    }

    public NodeBlock(String type, Node node) {
        super(type);
        NodeBlock cur = this;

        id = UUID.randomUUID();
        this.node = node;

        canvas.add(this);

        setSize(WIDTH, HEIGHT);

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 3) { // TODO only socket node.
                    Popup popup = new Popup();
                    int res = JOptionPane.showConfirmDialog(cur, popup, null, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);

                    if (res == JOptionPane.OK_OPTION) {
                        System.out.println(popup.getPortField().getText());
                        System.out.println(popup.getHostField().getText());
                    }
                }
                
                if (selectedNode == null) {
                    selectedNode = cur;
                }
                else if (selectedNode == cur) { // 만약 같은 노드 클릭하면 unselect 된다.
                    selectedNode = null;
                }
                else if (selectedNode != cur) {
                    // TODO 서로 연결 가능한지 확인하는 로직, (wirableNode인지, 연결 가능한가, 이미 연결이 되어 있는가?)

                    log.info("wire {} to {}", cur.id, selectedNode.id);

                    Wire wire = new Wire();

                    ((WirableNode) selectedNode.node).wire(wire);
                    ((WirableNode) cur.node).wire(wire);

                    canvas.repaint();

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
