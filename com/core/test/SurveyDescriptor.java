package com.core.test;

import com.core.lesson.*;
import org.json.*;

import java.awt.*;
import javax.swing.*;


public class SurveyDescriptor extends LessonPageDescriptor {


    public static final String IDENTIFIER = "FEEDBACK_PAGE";

    public SurveyDescriptor(JSONObject fb) {
        super(IDENTIFIER, new SurveyPage(fb));
    }


    public Object getBackPanelDescriptor() {
        return null;
    }

}
