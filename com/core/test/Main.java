package com.core.test;

import com.core.lesson.*;
import com.core.util.ResourceManager;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {


        Lesson lesson = new Lesson();
        lesson.getDialog().setTitle("Test Lesson Dialog");

        //login page to input student ID
        LoginDescriptor login = new LoginDescriptor();
        login.setNextPageDescriptor("2");
        //test page to test if recording is working
        RecordTestDescriptor recordtest = new RecordTestDescriptor();
        recordtest.setNextPageDescriptor("3");

        //speaking test pages
        int cnt = Integer.parseInt((String)ResourceManager.getTestResource("totalTests"));
        for(int i=0; i<cnt; i++) {
            LessonPageDescriptor descriptor = new SpeakingQuestionDescriptor();
            SpeakingQuestionPage page = ((SpeakingQuestionPage)descriptor.getPageComponent());
            page.setTestID(Integer.toString(i+1));
            page.setTotalTests(cnt);
            if (i < cnt-1) {
                descriptor.setNextPageDescriptor(Integer.toString(i+4));
            }
            lesson.registerLessonPage(Integer.toString(i+3), descriptor);
        }
        lesson.registerLessonPage("1", login);
        lesson.registerLessonPage("2", recordtest);

        lesson.setCurrentPage("3");

        int ret = lesson.showModalDialog();

        System.out.println("Dialog return code is (0=Finish,1=Error): " + ret);

        System.exit(0);

    }

}
