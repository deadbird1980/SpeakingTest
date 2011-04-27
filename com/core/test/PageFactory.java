package com.core.test;

import com.core.lesson.*;
import com.core.util.*;
import org.json.JSONObject;

public class PageFactory {
    public static LessonPageDescriptor createPage(String type, JSONObject json) {
        LessonPageDescriptor page;
        if (type.equals("LoginPage")) {
            page = new LoginDescriptor(json);
        } else if (type.equals("IntroPage")) {
            page = new IntroDescriptor(json);
        } else if (type.equals("SurveyPage")) {
            page = new SurveyDescriptor(json);
        } else if (type.equals("RecordTestPage")) {
            page = new RecordTestDescriptor(json);
        } else if (type.equals("FeedbackPage")) {
            page = new FeedbackDescriptor(json);
        } else if (type.equals("SpeakingQuestionPage")) {
            page = new SpeakingQuestionDescriptor(json);
        } else {
            page = new IntroDescriptor(json);
        }
        return page;
    }
}
