package com.core.test;

import com.core.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;


public class RecordTestPage extends JPanel {
    /**
     * The String-based action command for the 'Next' button.
     */
    public static final String RECORD_BUTTON_ACTION_COMMAND = "RecordButtonActionCommand";
    public static final String STOP_BUTTON_ACTION_COMMAND = "StopButtonActionCommand";
    public static final String PLAY_BUTTON_ACTION_COMMAND = "PlayButtonActionCommand";

    private JLabel blankSpace;

    private JButton recordButton;
    private JButton stopButton;
    private JButton playButton;

    private JLabel introLabel;
    private JPanel contentPanel;

    private RecordPlay recorder;

    public RecordTestPage() {

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

        blankSpace = new JLabel();
        introLabel = new JLabel();

        contentPanel1.setLayout(new java.awt.BorderLayout());

        introLabel.setFont(new java.awt.Font("MS Sans Serif", Font.BOLD, 11));
        introLabel.setText("situation 6 of 8");
        contentPanel1.add(introLabel, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        jPanel1.add(blankSpace);
        introLabel.setText("Please click record to start record and play to confirm");
        jPanel1.add(introLabel);
        recordButton = new JButton("RECORD");
        stopButton = new JButton("STOP");
        playButton = new JButton("PLAY");

        recordButton.setActionCommand(RECORD_BUTTON_ACTION_COMMAND);
        stopButton.setActionCommand(STOP_BUTTON_ACTION_COMMAND);

        //recordButton.addActionListener(LessonController);
        recordButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                stopButton.setEnabled(true) ;
                //start record
                RecordPlay recorder = getRecorder();
                if (!recorder.isStopped()) {
                    recorder.stop();
                    playButton.setEnabled(true) ;
                    recordButton.setText("RECORD") ;

                } else {
                    recorder.capture() ;
                    recordButton.setText("STOP") ;
                    playButton.setEnabled(false) ;
                }
            }
        }) ;
        //regist the PlayEvent
        playButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                //play record
                RecordPlay recorder = getRecorder();
                if (!recorder.isStopped())
                   recorder.stop();
                recorder.play() ;
            }
        }) ;
        //stopButton.addActionListener(LessonController);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(10, 0, 5, 0));

        //buttonBox.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));
        buttonsPanel.add(recordButton);
        //buttonsPanel.add(stopButton);
        buttonsPanel.add(playButton);

        jPanel1.add(buttonsPanel, java.awt.BorderLayout.EAST);

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

    private RecordPlay getRecorder() {
        if (recorder == null) {
            recorder = new RecordPlay();
        }
        return recorder;
    }

}
