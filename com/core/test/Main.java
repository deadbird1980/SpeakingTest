package com.core.test;

import com.core.lesson.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Lesson lesson = new Lesson();
        lesson.getDialog().setTitle("Test Lesson Dialog");

        LessonPageDescriptor descriptor1 = new SpeakingQuestionDescriptor();
        lesson.registerLessonPage(SpeakingQuestionDescriptor.IDENTIFIER, descriptor1);

        lesson.setCurrentPage(SpeakingQuestionDescriptor.IDENTIFIER);

        int ret = lesson.showModalDialog();

        System.out.println("Dialog return code is (0=Finish,1=Error): " + ret);

        System.exit(0);

    }

}
