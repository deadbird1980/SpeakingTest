package com.core.test;

import com.core.lesson.*;

import java.awt.*;
import javax.swing.*;


public class RecordTestDescriptor extends LessonPageDescriptor {


    public static final String IDENTIFIER = "LOGIN_PAGE";

    public RecordTestDescriptor() {
        super(IDENTIFIER, new LoginPage());
    }


    public Object getBackPanelDescriptor() {
        return null;
    }

}
