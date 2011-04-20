package com.core.test;

import com.core.lesson.*;
import com.core.util.ResourceManager;

import java.io.*;
import java.util.HashMap;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class Main {
    private static int testStartPage = 6;

    public static void main(String[] args) {


        Lesson lesson = new Lesson();
        lesson.getDialog().setTitle("Oral English Proficiency Test");
        //login page to input student ID
        LoginDescriptor login = new LoginDescriptor();
        login.setNextPageDescriptor("2");
        //test page to test if recording is working
        RecordTestDescriptor recordtest = new RecordTestDescriptor(ResourceManager.getPageText("intro1"), (String)ResourceManager.getTestAudio("introAudio_1"));
        recordtest.setNextPageDescriptor("3");
        //page 3-5
        IntroDescriptor intro2 = new IntroDescriptor(ResourceManager.getPageText("intro2"), (String)ResourceManager.getTestAudio("introAudio_2"));
        IntroDescriptor intro3 = new IntroDescriptor(ResourceManager.getPageText("intro3"), (String)ResourceManager.getTestAudio("introAudio_3"));
        IntroDescriptor intro4 = new IntroDescriptor(ResourceManager.getPageText("intro4"), (String)ResourceManager.getTestAudio("introAudio_4"));
        intro2.setNextPageDescriptor("4");
        intro3.setNextPageDescriptor("5");
        intro4.setNextPageDescriptor("6");

        //speaking test pages
        int cnt = Integer.parseInt((String)ResourceManager.getTestResource("totalTests"));
        for(int i=0; i<cnt; i++) {
            LessonPageDescriptor descriptor = new SpeakingQuestionDescriptor(Integer.toString(i+1), cnt);
            if (i < cnt-1) {
                descriptor.setNextPageDescriptor(Integer.toString(i+1+testStartPage));
            }
            lesson.registerLessonPage(Integer.toString(i+testStartPage), descriptor);
        }
        lesson.registerLessonPage("1", login);
        lesson.registerLessonPage("2", recordtest);
        lesson.registerLessonPage("3", intro2);
        lesson.registerLessonPage("4", intro3);
        lesson.registerLessonPage("5", intro4);

        lesson.setCurrentPage("1");

        int ret = lesson.showModalDialog();
        saveSubmitToFile(lesson, cnt);

        System.out.println("Dialog return code is (0=Finish,1=Error): " + ret);

        System.exit(0);

    }


    private static void saveSubmitToFile(Lesson lesson, int tests) {
        //get lesson submission
        HashMap submit = lesson.getModel().getPageSubmit();
        String userID = (String) ((HashMap)submit.get("1")).get("userID");
        try {
            // Create file
            ResourceManager.mvTempToUserPath(userID);
            String userFile = ResourceManager.getUserPath(userID)+"/"+userID+"_"+getToday()+".txt";
            FileWriter fstream = new FileWriter(userFile);
            BufferedWriter out = new BufferedWriter(fstream);
            //for (Object testID : submit.keySet()) {
            for(int i=0; i<tests; i++) {
                //HashMap pageSubmit = (HashMap)submit.get(testID);
                HashMap pageSubmit = (HashMap)submit.get(Integer.toString(i+testStartPage));
                if (pageSubmit == null)
                    continue;
                out.write("item " + (i+1) + ":\n");
                for (Object key : pageSubmit.keySet()) {
                     //System.out.println("test " + (i+1) + key +"="+pageSubmit.get(key)+"\n");
                     out.write(key +":"+pageSubmit.get(key) +"\n");
                }
                //copy record to user path
            }
            out.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }

    private static String getToday() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();       
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(today);
    }

}
