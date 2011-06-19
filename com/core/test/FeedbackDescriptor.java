package com.core.test;

import com.core.lesson.*;
import org.json.*;

import java.awt.*;
import javax.swing.*;


public class FeedbackDescriptor extends LessonPageDescriptor {


    public static final String IDENTIFIER = "FEEDBACK_PAGE";

    public FeedbackDescriptor(JSONObject fb) {
        super(IDENTIFIER, new FeedbackPage(fb));
    }

    public void aboutToDisplayPage() {
        if (!skippable)
            getLesson().setNextFinishButtonEnabled(Boolean.FALSE);
    }

    public Object getBackPanelDescriptor() {
        return null;
    }
    public void notifyMessage(String msg) {
        if (msg.equals("READY"))
            getLesson().setNextFinishButtonEnabled(Boolean.TRUE);
        else
            getLesson().setNextFinishButtonEnabled(Boolean.FALSE);
    }

}
