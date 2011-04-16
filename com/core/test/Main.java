package com.core.test;

import com.core.lesson.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Lesson lesson = new Lesson();
        lesson.getDialog().setTitle("Test Lesson Dialog");

        LessonPageDescriptor descriptor1 = new SpeakingQuestionDescriptor();
        descriptor1.setNextPageDescriptor("2");
        LessonPageDescriptor descriptor2 = new SpeakingQuestionDescriptor();
        descriptor2.setNextPageDescriptor("3");
        LessonPageDescriptor descriptor3 = new SpeakingQuestionDescriptor();
        lesson.registerLessonPage("1", descriptor1);
        lesson.registerLessonPage("2", descriptor2);
        lesson.registerLessonPage("3", descriptor3);

        //lesson.setCurrentPage(SpeakingQuestionDescriptor.IDENTIFIER);
        lesson.setCurrentPage("1");

        int ret = lesson.showModalDialog();

        System.out.println("Dialog return code is (0=Finish,1=Error): " + ret);

        System.exit(0);

    }

}
