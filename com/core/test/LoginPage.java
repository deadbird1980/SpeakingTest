package com.core.test;

import java.awt.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;


public class LoginPage extends JPanel {

    private JLabel blankSpace;
    private JLabel welcomeLabel;

    private JPanel contentPanel;

    public LoginPage() {

        contentPanel = getContentPanel();
        contentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        setLayout(new java.awt.BorderLayout());


        JPanel secondaryPanel = new JPanel();
        secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        add(secondaryPanel, BorderLayout.CENTER);
    }


    private JPanel getContentPanel() {

        JPanel contentPanel1 = new JPanel();
        JPanel jPanel1 = new JPanel();

        welcomeLabel = new JLabel();

        contentPanel1.setLayout(new java.awt.BorderLayout());

        welcomeLabel.setFont(new java.awt.Font("MS Sans Serif", Font.BOLD, 11));
        welcomeLabel.setText("Welcome to the Test");
        contentPanel1.add(welcomeLabel, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        blankSpace = new JLabel();
        jPanel1.add(blankSpace);
        contentPanel1.add(jPanel1, java.awt.BorderLayout.CENTER);

        return contentPanel1;

    }


    private Object getResource(String key) {

        URL url = null;
        String name = key;

        if (name != null) {

            try {
                Class c = Class.forName("com.nexes.test.Main");
                url = c.getResource(name);
            } catch (ClassNotFoundException cnfe) {
                System.err.println("Unable to find Main class");
            }
            return url;
        } else
            return null;

    }

}
