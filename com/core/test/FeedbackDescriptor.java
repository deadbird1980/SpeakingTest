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


    public Object getBackPanelDescriptor() {
        return null;
    }

}
