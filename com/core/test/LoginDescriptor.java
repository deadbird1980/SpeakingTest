package com.core.test;

import com.core.lesson.*;

import java.awt.*;
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
        //getPageComponent().sumit

    }

}
