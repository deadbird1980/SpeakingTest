package com.core.test;

import com.core.lesson.*;

import java.awt.*;
import java.beans.*;
import javax.swing.*;


public class LoginDescriptor extends LessonPageDescriptor {


    public static final String IDENTIFIER = "LOGIN_PAGE";

    public LoginDescriptor() {
        super(IDENTIFIER, new LoginPage());
    }


    public Object getBackPanelDescriptor() {
        return null;
    }

    public void aboutToHidePage() {
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

}
