package com.core.test;

import com.core.util.*;
import com.core.lesson.*;
import java.awt.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;


public class IntroPage extends LessonPage {

    private JLabel blankSpace;
    private JLabel welcomeLabel;
    //private JTextArea introArea;
    private JEditorPane introArea;

    private JPanel contentPanel;

    public IntroPage() {

        contentPanel = getContentPanel();
        contentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        setLayout(new java.awt.BorderLayout());

        JPanel secondaryPanel = new JPanel();
        secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        add(secondaryPanel, BorderLayout.CENTER);
    }


    private JPanel getContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout());
        JPanel introPanel = createIntroPanel();

        //contentPanel.add(introPanel, java.awt.BorderLayout.NORTH);
        contentPanel.add(introPanel);

        return contentPanel;

    }

    private JPanel createIntroPanel() {
        JPanel introPanel = new JPanel();
        introPanel.setBorder (new LineBorder(Color.blue, 3));
        introPanel.setPreferredSize(new Dimension(600, 400));
        introPanel.setLayout(new GridLayout());
        //introArea = new JTextArea();
        introArea = new JEditorPane();
        //introArea.setLineWrap(true);
        introArea.setEditable(false);
        introArea.setContentType("text/html; charset=EUC-JP");
        introArea.setText(ResourceManager.getPageText("intro"));
        //Get JFrame background color
        Color color = getBackground();
        introArea.setBackground(color);
        //introPanel.add(introArea, java.awt.BorderLayout.CENTER);
        introPanel.add(introArea);
        return introPanel;
    }

}
