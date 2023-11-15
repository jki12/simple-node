package com.example.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.example.CodeBuilder;
import com.example.node.Node;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App extends JFrame {
    private static final String TITLE = "simple tester";
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    public App() {
        this(WIDTH, HEIGHT);
    }

    public JScrollPane generatJScrollPane(JPanel c) {
        final String[] listData = { "TerminalInputNode", "TerminalOutputNode", "ServerNode", "EchoServerNode" };
        JList<String> list = new JList<>(listData);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // if (!e.getValueIsAdjusting()) System.out.println(list.getSelectedValue());
            }
        });

        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // double click event.
                    String selectedValue = list.getSelectedValue();
                    System.out.println("occured double click event - created node " + selectedValue); // TODO log.

                    NodeBlock nodeBlock = null;

                    try {
                        Class<?> clazz = Class.forName("com.example.node." + selectedValue);
                        Constructor<?> ctor = clazz.getConstructor(String.class);

                        var node = ctor.newInstance("node" + Node.getCount());

                        assert (node instanceof Node);
                        CodeBuilder.add((Node) node);

                        nodeBlock = new NodeBlock(selectedValue, (Node) node);

                    } catch (Exception ex) {
                        log.error(ex.getMessage());
                    }

                    c.add(nodeBlock);
                    c.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        JScrollPane scrollPane = new JScrollPane(list);

        return scrollPane;
    }

    public App(final int width, final int height) {
        super(TITLE);

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        Container c = getContentPane();

        setSize(width, height);
        c.setLayout(new GridBagLayout());

        gbc.insets = new Insets(10, 5, 10, 5);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;

        var canvas = new Canvas();
        NodeBlock.set(canvas); // node간 연결시 선을 그려주기 위해 캔버스를 set 해줌.
        canvas.setPreferredSize(new Dimension((int) (getWidth() * 0.75), getHeight()));

        c.add(canvas, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        var scrollPane = generatJScrollPane(canvas);
        scrollPane.setPreferredSize(new Dimension(200, getHeight()));

        c.add(scrollPane, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;

        var runButton = new JButton("run");

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = CodeBuilder.getCode(); // TODO and compile, run.

                File file = new File("Test.java");
                if (file.exists()) file.delete();

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    file.createNewFile();

                    bw.write(s);
                    bw.flush();

                } catch (Exception ignore) {
                }
            }
        });

        c.add(runButton, gbc);
        pack();
    }
}
