package com.core.test;

import com.core.lesson.*;

import java.awt.*;
import javax.swing.*;
import org.json.JSONObject;
import org.json.JSONException;


public class IntroDescriptor extends LessonPageDescriptor {


    public static final String IDENTIFIER = "INTRO_PAGE";

    public IntroDescriptor(JSONObject json) {
        super(IDENTIFIER, new IntroPage(json));
    }


    public Object getBackPanelDescriptor() {
        return null;
    }

    public void aboutToHidePage() {
        //save study Information
        IntroPage page = (IntroPage) getPageComponent();
        page.stopAudio();

    }

    public void aboutToDisplayPage() {
        IntroPage page = (IntroPage) getPageComponent();
        page.playAudio();
    }


}
