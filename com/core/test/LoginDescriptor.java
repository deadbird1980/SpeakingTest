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
        //save study Information

    }

    public void aboutToDisplayPage() {
        getLessonModel().setNextFinishButtonEnabled(Boolean.FALSE);
    }

    public void notifyMessage(String msg) {
        if (msg.equals("READY")) {
            getLessonModel().setNextFinishButtonEnabled(Boolean.TRUE);
        }
    }


}
