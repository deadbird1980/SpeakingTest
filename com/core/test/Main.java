package com.core.test;

import com.core.lesson.*;

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
        LessonPageDescriptor descriptor1 = new SpeakingQuestionDescriptor();
        //descriptor1.setNextPageDescriptor("4");
        LessonPageDescriptor descriptor2 = new SpeakingQuestionDescriptor();
        descriptor2.setNextPageDescriptor("5");
        LessonPageDescriptor descriptor3 = new SpeakingQuestionDescriptor();
        lesson.registerLessonPage("1", login);
        lesson.registerLessonPage("2", recordtest);
        lesson.registerLessonPage("3", descriptor1);

        //lesson.setCurrentPage(SpeakingQuestionDescriptor.IDENTIFIER);
        lesson.setCurrentPage("2");

        int ret = lesson.showModalDialog();

        System.out.println("Dialog return code is (0=Finish,1=Error): " + ret);

        System.exit(0);

    }

}
