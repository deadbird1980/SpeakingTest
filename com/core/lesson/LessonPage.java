package com.core.lesson;

import java.util.*;
import javax.swing.*;
import javax.swing.border.*;


public class LessonPage extends JPanel {

    public static final String STATUS_PROPERTY = "statusProperty";
    public LessonPageDescriptor listener;
    public boolean skipped;
    private int pageNO;

    public void LessonPage() {
    }

    public void setListener(LessonPageDescriptor des) {
        listener = des;
    }

    public void setPageNO(int no) {
        pageNO = no;
    }
    /**
     * Override this class to provide the Object-based identifier of the page that the
     * user should traverse to when the Back button is pressed. Note that this method
     **/
    public HashMap getSubmit() {
        return null;
    }

    protected void sendMessage(String msg) {
        listener.notifyMessage(msg);
    }


}
