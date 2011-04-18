package com.core.test;

import com.core.lesson.*;
import com.core.util.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javazoom.jl.player.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;


public class SpeakingQuestionPage extends LessonPage {
    /**
     * The String-based action command for the 'Next' button.
     */
    public static final String RECORD_BUTTON_ACTION_COMMAND = "RecordButtonActionCommand";
    public static final String STOP_BUTTON_ACTION_COMMAND = "StopButtonActionCommand";

    private JLabel blankSpace;
    private JLabel pagePositionLabel;
    private JTextArea questionLabel;
    private JTextArea questionTranslationLabel;

    private JButton recordButton;
    private JButton stopButton;

    private JLabel positionLabel;
    private JLabel countDownLabel;
    private JPanel contentPanel;

    private Thread countDownTimer;
    private Thread playAudio;
    private RecordPlay recorder;

    //Tests model data
    private String TestID = "1";
    private int totalTests = 1;

    public SpeakingQuestionPage(String id, int tests) {
        TestID = id;
        totalTests = tests;

        contentPanel = getContentPanel();
        contentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel secondaryPanel = new JPanel();
        secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        add(secondaryPanel, BorderLayout.CENTER);
    }


    private JPanel getContentPanel() {

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel jPanel1 = new JPanel();

        blankSpace = new JLabel();

        contentPanel.setLayout(new javax.swing.BoxLayout(contentPanel, BoxLayout.Y_AXIS));


        //buttonBox.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));

        JPanel headerPanel = createHeaderPanel();
        JPanel questionPanel = createQuestionPanel();
        JPanel recordPanel = createRecordButtons();
        JPanel questionTranslationPanel = createQuestionTranslationPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        contentPanel.add(headerPanel, c);
        contentPanel.add(questionPanel, c);
        contentPanel.add(recordPanel, c);
        contentPanel.add(questionTranslationPanel, c);

        return contentPanel;

    }


    private Object getResource(String key) {

        URL url = null;
        String name = key;

        if (name != null) {

            try {
                Class c = Class.forName("com.core.test.Main");
                url = c.getResource(name);
            } catch (ClassNotFoundException cnfe) {
                System.err.println("Unable to find Main class");
            }
            return url;
        } else
            return null;

    }

    public void setTestID(String id) {
        TestID = id;
    }

    public void setTotalTests(int cnt) {
        totalTests = cnt;
    }

    public void startTimer() {
        class countDownThread extends Thread {

            public void run(){
                TimerThread count_down = new TimerThread(TimerThread.COUNT_DOWN);
                count_down.start();

                while (count_down.isAlive()) {
                    countDownLabel.setText((String)ResourceManager.getTestResource("timeLeft")+count_down.getClock());
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted.");
                    }
                }
            }

        }
        if (countDownTimer != null) {
            countDownTimer.interrupt();
        } else {
            countDownTimer = new countDownThread();
        }
        countDownTimer.start();
    }

    public void playAudio() {
        class playAudioThread extends Thread {
            private Player player;

            public void interrupt() {
                player.close();
                //stop the timer
                stopTimer();
                super.interrupt();
            }

            public void run(){
                String fFilename = getQuestionAudioID();
                try {
                    if ((new File(fFilename)).exists()) {
                        FileInputStream fin = new FileInputStream(fFilename);
                        BufferedInputStream bin = new BufferedInputStream(fin);
                        AudioDevice dev = FactoryRegistry.systemRegistry().createAudioDevice();
                        player = new Player(bin);
                        player.play();
                        //after end of playing
                        startTimer();
                        System.out.println("playing "+fFilename+"...");
                    } else {
                        System.out.println("not exist file "+fFilename+"...");
                    }
                } catch (IOException ex) {
                    //throw new Exception("Problem playing file "+fFilename, ex);
                    System.out.println("Problem playing file "+fFilename);
                } catch (Exception ex) {
                    //throw new Exception("Problem playing file "+fFilename, ex);
                    System.out.println("Problem playing file "+fFilename);
                }
            }
        }
        try {
            playAudioThread playAudio = new playAudioThread();
            playAudio.start();
        } catch (Exception ex) {
            System.out.println("Problem playing file");
        }
    }

    public void stopAudio() {
        if (playAudio != null) {
            playAudio.interrupt();
        }
    }

    public void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.interrupt();
        }
    }

    private JPanel createRecordButtons() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(10, 0, 5, 0));
        buttonsPanel.setPreferredSize(new Dimension(400, 60));
        recordButton = new JButton((String)ResourceManager.getTestResource("record"));
        stopButton = new JButton((String)ResourceManager.getTestResource("stop"));
        stopButton.setEnabled(false) ;

        recordButton.setActionCommand(RECORD_BUTTON_ACTION_COMMAND);
        stopButton.setActionCommand(STOP_BUTTON_ACTION_COMMAND);

        //recordButton.addActionListener(LessonController);
        recordButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                stopButton.setEnabled(true) ;
                //start record
                RecordPlay recorder = getRecorder();
                recorder.capture() ;
                //recording countdown start
                startTimer();
            }
        }) ;
        stopButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                recordButton.setEnabled(true) ;
                recorder.stop() ;
                //stop the countdown
                if (countDownTimer != null) {
                    countDownTimer.interrupt();
                }
            }
        }) ;
        buttonsPanel.add(recordButton);
        buttonsPanel.add(stopButton);
        return buttonsPanel;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(400, 40));

        headerPanel.setLayout(new java.awt.BorderLayout());
        positionLabel = new JLabel();
        positionLabel.setFont(new java.awt.Font("MS Sans Serif", Font.BOLD, 11));
        setPosition();
        countDownLabel = new JLabel();
        //countDownLabel.setText("02:00");
        headerPanel.add(positionLabel, java.awt.BorderLayout.WEST);
        headerPanel.add(countDownLabel, java.awt.BorderLayout.EAST);
        return headerPanel;
    }

    private void setPosition() {
        String position = (String) ResourceManager.getTestResource("indicator");
        position = position.replace("%currentTest%", TestID);
        position = position.replace("%totalTests%", Integer.toString(totalTests));
        positionLabel.setText(position);
    }
    private JPanel createQuestionPanel() {
        JPanel questionPanel = new JPanel();
        questionPanel.setPreferredSize(new Dimension(400, 100));
        questionPanel.setLayout(new java.awt.BorderLayout());
        questionLabel = new JTextArea();
        questionLabel.setText(getQuestionID());
        questionLabel.setLineWrap(true);
        questionLabel.setEditable(false);
        //Get JFrame background color  
        Color color = getBackground();
        questionLabel.setBackground(color);
        questionPanel.add(questionLabel, java.awt.BorderLayout.CENTER);
        return questionPanel;
    }

    private JPanel createQuestionTranslationPanel() {
        JPanel questionPanel = new JPanel();
        questionPanel.setPreferredSize(new Dimension(400, 100));
        questionPanel.setLayout(new java.awt.BorderLayout());
        questionTranslationLabel = new JTextArea();
        questionTranslationLabel.setText(getQuestionTranslationID());
        questionTranslationLabel.setLineWrap(true);
        questionTranslationLabel.setEditable(false);
        //Get JFrame background color  
        Color color = getBackground();
        questionTranslationLabel.setBackground(color);
        questionPanel.add(questionTranslationLabel, java.awt.BorderLayout.CENTER);
        return questionPanel;
    }
    private RecordPlay getRecorder() {
        if (recorder == null) {
            recorder = new RecordPlay();
        }
        return recorder;
    }

    private String getQuestionID() {
        return (String)ResourceManager.getTestResource("question_"+TestID);
    }

    private String getQuestionAudioID() {
        return (String)ResourceManager.getTestResource("questionAudio_"+TestID);
    }

    private String getQuestionTranslationID() {
        return (String)ResourceManager.getTestResource("questionTranslation_"+TestID);
    }



}
