package com.core.test;

import java.awt.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;


public class LoginPage extends JPanel {

    private JLabel blankSpace;
    private JLabel welcomeLabel;
    private JTextField userTextField;

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
        welcomeLabel.setText((String)getResource("welcomeMessage"));
        contentPanel1.add(welcomeLabel, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        blankSpace = new JLabel();
        jPanel1.add(blankSpace);
        JPanel userPanel = new JPanel(new BorderLayout());
        JLabel userLabel = new JLabel((String)getResource("userID"));
        JTextField userTextField = new JTextField();
        userLabel.setLabelFor(userTextField);
        userPanel.add(userLabel, BorderLayout.WEST);
        userPanel.add(userTextField, BorderLayout.CENTER);
        jPanel1.add(userPanel, BorderLayout.SOUTH);

        contentPanel1.add(jPanel1, java.awt.BorderLayout.CENTER);

        return contentPanel1;

    }


    private Object getResource(String key) {
        try {

            PropertyResourceBundle resources = (PropertyResourceBundle)
                ResourceBundle.getBundle("com.core.test.test");

            return (String)(resources.getObject(key));
        } catch (MissingResourceException mre) {
            System.out.println(mre);
            System.exit(1);
        }

        return null;

    }

}
