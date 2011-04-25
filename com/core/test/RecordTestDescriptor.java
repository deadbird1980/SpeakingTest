package com.core.test;

import com.core.lesson.*;

import java.awt.*;
import org.json.JSONObject;
import org.json.JSONException;


public class RecordTestDescriptor extends LessonPageDescriptor {


    public static final String IDENTIFIER = "LOGIN_PAGE";

    public RecordTestDescriptor(JSONObject json) {
        super(IDENTIFIER, new RecordTestPage(json));
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
