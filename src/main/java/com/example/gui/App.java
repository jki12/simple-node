package com.example.gui;

import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class App extends JFrame {
    private static final String TITLE = "tester";
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    public App() {
        this(WIDTH, HEIGHT);
    }

    public JScrollPane generatJScrollPane() {
        final String[] listData = { "terminal input", "terminal output" };
        JList<String> list = new JList<>(listData);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // int selected = -1;
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) System.out.println(list.getSelectedValue());
            }
        });

        list.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // add double click event.
                    System.out.println("occured double click event - created node" + list.getSelectedValue());
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
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        Container c = getContentPane();

        setSize(width, height);
        c.setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 5, 10, 5);

        var scrollPane = generatJScrollPane();
        scrollPane.setPreferredSize(new Dimension(260, getHeight()));

        c.add(scrollPane, gbc);


        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;

        var a = new JButton(); // to canvas.
        a.setPreferredSize(new Dimension((int) (getWidth() * 0.75), getHeight()));

        a.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    a.add(new Node("hey"));
                }
            }
        );

        c.add(a, gbc);

        pack();
    }
}
