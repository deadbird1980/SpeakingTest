package com.core.test;

import com.core.lesson.*;

import java.awt.*;
import javax.swing.*;


public class SpeakingQuestionDescriptor extends LessonPageDescriptor {

    public static final String IDENTIFIER = "INTRODUCTION_PANEL";

    public SpeakingQuestionDescriptor() {
        super(IDENTIFIER, new SpeakingQuestionPage());
    }

    public Object getNextPanelDescriptor() {
        return SpeakingQuestionDescriptor.IDENTIFIER;
    }

    public Object getBackPanelDescriptor() {
        return null;
    }

}
