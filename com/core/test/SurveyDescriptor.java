package com.core.test;

import com.core.lesson.*;
import com.core.util.ResourceManager;
import org.json.*;

import java.util.HashMap;
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

    public void displayingPage() {
        //enable continue button untill page completed
        SurveyPage page = (SurveyPage) getPageComponent();
        String userID = (String) getLesson().getModel().getSubmit("1").get("userID");
        //System.out.println(userID+":" +page.place+":");
        if ((userID.toLowerCase().startsWith("c") && !page.place.toLowerCase().equals("china")) ||
        (userID.toLowerCase().startsWith("e") && page.place.toLowerCase().equals("china"))) {
            System.out.println("skip this page");
            getLesson().setCurrentPage(getNextPageDescriptor());
            page.skipped = true;
        }
    }

    public void aboutToDisplayPage() {
        SurveyPage page = (SurveyPage) getPageComponent();
        //enable continue button untill page completed
        getLesson().setNextFinishButtonEnabled(Boolean.FALSE);
    }

    public void notifyMessage(String msg) {
        if (msg.equals("READY")) {
            getLesson().setNextFinishButtonEnabled(Boolean.TRUE);
        } else {
            getLesson().setNextFinishButtonEnabled(Boolean.FALSE);
        }
    }

}
