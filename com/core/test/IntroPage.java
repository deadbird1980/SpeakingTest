package com.core.test;

import com.core.util.*;
import com.core.lesson.*;
import javazoom.jl.player.*;
import java.awt.*;
import java.io.*;
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
    private playAudioThread playAudio;

    public String audioFile="";
    public String textContent="";

    public IntroPage(String textStr, String audioFileName) {
        audioFile = audioFileName;
        textContent = textStr;

        contentPanel = getContentPanel();
        contentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        setLayout(new java.awt.BorderLayout());

        JPanel secondaryPanel = new JPanel();
        secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        add(secondaryPanel, BorderLayout.CENTER);
    }

    class playAudioThread extends Thread {
        // Must be volatile:
        private volatile boolean stop = false;
        private Player player;
        private void playFile(String fFilename) {

            try {
                if ((new File(fFilename)).exists()) {
                    FileInputStream fin = new FileInputStream(fFilename);
                    BufferedInputStream bin = new BufferedInputStream(fin);
                    AudioDevice dev = FactoryRegistry.systemRegistry().createAudioDevice();
                    player = new Player(bin);
                    player.play();
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

        public void run(){
            playFile(audioFile);
        }

        public void requestStop() {
            //stop the timer
            if (player != null)
                player.close();
        }
    }

    public void stopAudio() {
        if (playAudio != null) {
            playAudio.requestStop();
        }
    }

    public void playAudio() {
        try {
            playAudio = new playAudioThread();
            playAudio.start();
        } catch (Exception ex) {
            System.out.println("Problem playing file");
        }
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
        //introPanel.setBorder (new LineBorder(Color.blue, 3));
        introPanel.setPreferredSize(new Dimension(600, 400));
        introPanel.setLayout(new GridLayout());
        //introArea = new JTextArea();
        introArea = new JEditorPane();
        //introArea.setLineWrap(true);
        introArea.setEditable(false);
        introArea.setContentType("text/html; charset=utf-8");
        introArea.setText(textContent);
        //Get JFrame background color
        Color color = getBackground();
        introArea.setBackground(color);
        //introPanel.add(introArea, java.awt.BorderLayout.CENTER);
        introPanel.add(introArea);
        return introPanel;
    }


}
