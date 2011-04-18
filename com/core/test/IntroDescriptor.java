package com.core.test;

import com.core.lesson.*;

import java.awt.*;
import javax.swing.*;


public class IntroDescriptor extends LessonPageDescriptor {


    public static final String IDENTIFIER = "INTRO_PAGE";

    public IntroDescriptor() {
        super(IDENTIFIER, new IntroPage());
    }


    public Object getBackPanelDescriptor() {
        return null;
    }

    public void aboutToHidePage() {
        //save study Information

    }

}
