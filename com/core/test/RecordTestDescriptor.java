package com.core.test;

import com.core.lesson.*;

import java.awt.*;
import javax.swing.*;


public class RecordTestDescriptor extends LessonPageDescriptor {


    public static final String IDENTIFIER = "LOGIN_PAGE";

    public RecordTestDescriptor(String text, String audio) {
        super(IDENTIFIER, new RecordTestPage(text, audio));
    }


    public Object getBackPanelDescriptor() {
        return null;
    }

    public void aboutToHidePage() {
        //save study Information
        IntroPage page = (IntroPage) getPageComponent();
        page.stopAudio();

    }

    public void aboutToDisplayPage() {
        IntroPage page = (IntroPage) getPageComponent();
        page.playAudio();
    }

}
