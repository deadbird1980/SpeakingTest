package com.core.test;

import com.core.lesson.*;
import com.core.util.*;
import org.json.JSONObject;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;


public class LoginPage extends LessonPage {

    private JLabel blankSpace;
    private JLabel welcomeLabel;
    private JTextField userTextField;

    private JPanel contentPanel;
    private String userID = "";

    public LoginPage(JSONObject json) {

        super();
        contentPanel = getContentPanel();
        contentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        setLayout(new java.awt.BorderLayout());


        JPanel secondaryPanel = new JPanel();
        secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        add(secondaryPanel, BorderLayout.CENTER);
    }


    public String getUserID() {
        return userID;
    }

    public HashMap getSubmit() {
        HashMap data = new HashMap();
        data.put("userID", userID);
        return data;
    }


    private JPanel getContentPanel() {

        JPanel contentPanel1 = new JPanel();
        JPanel jPanel1 = new JPanel();
        JLabel textLabel = new JLabel();
        textLabel.setBackground(Color.gray);
        textLabel.setFont(new Font("MS Sans Serif", Font.BOLD, 20));
        textLabel.setText("Oral Completion Test");
        textLabel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        textLabel.setOpaque(true);
        welcomeLabel = new JLabel();

        contentPanel1.setLayout(new java.awt.BorderLayout());

        welcomeLabel.setFont(new java.awt.Font("MS Sans Serif", Font.BOLD, 11));
        welcomeLabel.setText((String)ResourceManager.getTestResource("welcomeMessage"));
        contentPanel1.add(textLabel, java.awt.BorderLayout.NORTH);
        contentPanel1.add(welcomeLabel, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        blankSpace = new JLabel();
        jPanel1.add(blankSpace);
        JPanel userPanel = new JPanel(new BorderLayout());
        JLabel userLabel = new JLabel((String)ResourceManager.getTestResource("userID"));
        userTextField = new JTextField();
        userLabel.setLabelFor(userTextField);
        userPanel.add(userLabel, BorderLayout.WEST);
        userPanel.add(userTextField, BorderLayout.CENTER);
        jPanel1.add(userPanel, BorderLayout.SOUTH);
        //add listener
        KeyListener keyListener = new KeyListener() {
            public void keyPressed(KeyEvent keyEvent) {
            }
            public void keyReleased(KeyEvent keyEvent) {
                userID = userTextField.getText();
                //System.out.println("userID:"+userID);
                //check the style
                if (userID.length() > 3)
                    sendMessage("READY");
                else
                    sendMessage("NOT_READY");
            }
            public void keyTyped(KeyEvent keyEvent) {
            }
        };
        userTextField.addKeyListener(keyListener);

        contentPanel1.add(jPanel1, java.awt.BorderLayout.CENTER);

        return contentPanel1;

    }

}
