package com.core.test;

import com.core.lesson.*;
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
        HashMap submit = getLesson().getModel().getPageSubmit();
        String userID = (String) ((HashMap)submit.get("1")).get("userID");
        //System.out.println(userID+":" +page.place+":");
        if ((userID.toLowerCase().startsWith("c") && !page.place.toLowerCase().equals("china")) ||
        (userID.toLowerCase().startsWith("e") && page.place.toLowerCase().equals("china"))) {
            //System.out.println("skip this page");
            getLesson().setCurrentPage(getNextPageDescriptor());
            page.skipped = true;
        }
    }

}
