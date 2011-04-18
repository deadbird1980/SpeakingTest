package com.core.lesson;

import javax.swing.*;
import javax.swing.border.*;


public class LessonPage extends JPanel {

    public static final String STATUS_PROPERTY = "statusProperty";
    public LessonPageDescriptor listener;
    private Integer pageNO;

    public void LessonPage() {
    }

    public void setListener(LessonPageDescriptor des) {
        listener = des;
    }

    protected void sendMessage(String msg) {
        listener.notifyMessage(msg);
    }

}
