package com.core.test;

import com.core.util.EventListener;
import com.core.util.ResourceManager;
import com.core.lesson.*;
import javazoom.jl.player.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import org.json.JSONObject;
import org.json.JSONException;


public class IntroPage extends LessonPage implements com.core.util.EventListener{

    private JLabel blankSpace;
    private JLabel welcomeLabel;
    //private JTextArea introArea;
    private JEditorPane introArea;

    private JPanel contentPanel;
    private playAudioThread playAudio;

    public String audioFile="";
    public String textContent="";

    public IntroPage(JSONObject json) {
        try {
            textContent = ResourceManager.getPageText(json.getString("page"));
            audioFile = ResourceManager.getTestAudio(json.getString("audio"));
        } catch (JSONException e) {
            System.out.println("page or audio information is not set in application.json file");
        }

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
        private EventListener listener;
        private String audioFile;
        public playAudioThread(String fFilename) {
            audioFile = fFilename;
        }

        public void addListener(EventListener lsn) {
            listener = lsn;
        }
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
            if (listener != null)
                listener.eventTriggered("playDone");
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
            playAudio = new playAudioThread(audioFile);
            playAudio.addListener(this);
            playAudio.start();
        } catch (Exception ex) {
            System.out.println("Problem playing file");
        }
    }

    public void eventTriggered(String event){
        if (event.equals("playDone")) {
            //play audio DONE
            sendMessage("READY");
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
