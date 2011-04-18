package com.core.test;

import com.core.lesson.*;

import java.awt.*;
import javax.swing.*;


public class SpeakingQuestionDescriptor extends LessonPageDescriptor {


    public static final String IDENTIFIER = "INTRODUCTION_PANEL";

    public SpeakingQuestionDescriptor() {
        super(IDENTIFIER, new SpeakingQuestionPage());
    }


    public Object getBackPanelDescriptor() {
        return null;
    }

    public void aboutToDisplayPage() {
        SpeakingQuestionPage page = (SpeakingQuestionPage) getPageComponent();
        page.playAudio();
    }

    public void aboutToHidePage() {
        SpeakingQuestionPage page = (SpeakingQuestionPage) getPageComponent();
        page.stopAudio();
        page.stopTimer();
    }

}
