package com.core.test;

import com.core.lesson.*;

import java.awt.*;
import javax.swing.*;


public class IntroDescriptor extends LessonPageDescriptor {


    public static final String IDENTIFIER = "INTRO_PAGE";

    public IntroDescriptor(String text, String audioFile) {
        super(IDENTIFIER, new IntroPage(text, audioFile));
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
