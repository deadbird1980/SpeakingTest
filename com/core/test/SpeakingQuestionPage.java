package com.core.test;

import java.awt.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;


public class SpeakingQuestionPage extends JPanel {
    /**
     * The String-based action command for the 'Next' button.
     */
    public static final String RECORD_BUTTON_ACTION_COMMAND = "RecordButtonActionCommand";
    public static final String STOP_BUTTON_ACTION_COMMAND = "StopButtonActionCommand";

    private JLabel blankSpace;
    private JLabel pagePositionLabel;
    private JLabel questionLabel;
    private JLabel questionTranslationLabel;

    private JButton recordButton;
    private JButton stopButton;

    private JLabel positionLabel;
    private JPanel contentPanel;

    public SpeakingQuestionPage() {

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

        positionLabel = new JLabel();
        blankSpace = new JLabel();
        questionLabel = new JLabel();
        questionTranslationLabel = new JLabel();

        contentPanel1.setLayout(new java.awt.BorderLayout());

        positionLabel.setFont(new java.awt.Font("MS Sans Serif", Font.BOLD, 11));
        positionLabel.setText("situation 6 of 8");
        contentPanel1.add(positionLabel, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        jPanel1.add(blankSpace);
        questionLabel.setText("This is an example of a wizard dialog, which allows a user to traverse");
        questionTranslationLabel.setText("llows a user to traverse");
        jPanel1.add(questionLabel);
        recordButton = new JButton("RECORD");
        stopButton = new JButton("STOP");

        recordButton.setActionCommand(RECORD_BUTTON_ACTION_COMMAND);
        stopButton.setActionCommand(STOP_BUTTON_ACTION_COMMAND);

        //recordButton.addActionListener(LessonController);
        //stopButton.addActionListener(LessonController);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(10, 0, 5, 0));

        //buttonBox.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
        buttonsPanel.add(recordButton);
        buttonsPanel.add(stopButton);

        jPanel1.add(buttonsPanel, java.awt.BorderLayout.EAST);
        jPanel1.add(questionTranslationLabel);

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
