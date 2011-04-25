package com.core.test;

import com.core.lesson.*;

import java.awt.*;
import javax.swing.*;
import org.json.JSONObject;


public class SpeakingQuestionDescriptor extends LessonPageDescriptor {


    public static final String IDENTIFIER = "SPEAKING_QUESTION";

    public SpeakingQuestionDescriptor(JSONObject json) {
        super(IDENTIFIER, new SpeakingQuestionPage(json));
    }


    public Object getBackPanelDescriptor() {
        return null;
    }

    public void aboutToDisplayPage() {
        SpeakingQuestionPage page = (SpeakingQuestionPage) getPageComponent();
        page.playAudio();
        //enable continue button untill page completed
        getLesson().setNextFinishButtonEnabled(Boolean.FALSE);
    }

    public void aboutToHidePage() {
        SpeakingQuestionPage page = (SpeakingQuestionPage) getPageComponent();
        page.stopAudio();
        page.stopTimer();
    }

    public void notifyMessage(String msg) {
        if (msg.equals("READY")) {
            getLesson().setNextFinishButtonEnabled(Boolean.TRUE);
        } else {
            getLesson().setNextFinishButtonEnabled(Boolean.FALSE);
        }
    }

}
