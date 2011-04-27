package com.core.test;

import com.core.lesson.*;

import com.core.util.ResourceManager;
import java.awt.*;
import java.beans.*;
import org.json.JSONObject;


public class LoginDescriptor extends LessonPageDescriptor {


    public static final String IDENTIFIER = "LOGIN_PAGE";

    public LoginDescriptor(JSONObject json) {
        super(IDENTIFIER, new LoginPage(json));
    }


    public Object getBackPanelDescriptor() {
        return null;
    }

    public void aboutToHidePage() {
        LoginPage page = (LoginPage) getPageComponent();
        ResourceManager.setUserPath(page.getUserID());

    }

    public void aboutToDisplayPage() {
        getLesson().setNextFinishButtonEnabled(Boolean.FALSE);
    }

    public void notifyMessage(String msg) {
        if (msg.equals("READY"))
            getLesson().setNextFinishButtonEnabled(Boolean.TRUE);
        else
            getLesson().setNextFinishButtonEnabled(Boolean.FALSE);
    }

    public String getUserID() {
        LoginPage page = (LoginPage) getPageComponent();
        return page.getUserID();
    }

}
