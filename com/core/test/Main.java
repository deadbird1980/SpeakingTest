package com.core.test;

import com.core.lesson.*;
import com.core.util.ResourceManager;
import com.core.util.Utils;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;

import java.io.*;
import java.util.HashMap;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main {
    private static int testStartPage = 6;

    public static void main(String[] args) {


        Lesson lesson = new Lesson();
        JSONObject application = ResourceManager.getJSON("application.json");
        try {
            lesson.getDialog().setTitle(application.getString("title"));
            //pages
            JSONArray pages = application.getJSONArray("pages");
            int totalTest = 0;
            for(int i=0; i<pages.length(); i++) {
                JSONObject page = pages.getJSONObject(i);
                String type = page.getString("type");
                if (type.equals("SpeakingQuestionPage")) {
                    totalTest++;
                }
            }
            int testCnt = 0;
            for(int i=0; i<pages.length(); i++) {
                JSONObject page = pages.getJSONObject(i);
                String type = page.getString("type");
                if (type.equals("SpeakingQuestionPage")) {
                    page.put("totalTest", totalTest);
                    page.put("id", testCnt);
                }
                System.out.println("type = "+type);
                LessonPageDescriptor p = PageFactory.createPage(type, page);
                p.setNextPageDescriptor(Integer.toString(i+2));
                lesson.registerLessonPage(Integer.toString(i+1), p);
            }
        } catch (JSONException e) {
            lesson.getDialog().setTitle("Oral Completion Test");
        }
        lesson.setCurrentPage("1");

        int ret = lesson.showModalDialog();
        //saveTestSubmitToFile(lesson, cnt);
        //saveQuery(lesson);
        //saveSurvey(lesson);

        System.out.println("Dialog return code is (0=Finish,1=Error): " + ret);

        System.exit(0);

    }


    private static void saveTestSubmitToFile(Lesson lesson, int tests) {
        //get lesson submission
        HashMap submit = lesson.getModel().getPageSubmit();
        String userID = (String) ((HashMap)submit.get("1")).get("userID");
        try {
            // Create file
            ResourceManager.mvTempToUserPath(userID);
            String userFile = ResourceManager.getUserPath(userID)+"/"+userID+"_"+Utils.getToday()+".txt";
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
                    if (key == "recordFile")
                        continue;
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

    private static void saveQuery(Lesson lesson) {
        //get lesson submission
        HashMap submit = lesson.getModel().getPageSubmit();
        String userID = (String) ((HashMap)submit.get("1")).get("userID");
        try {
            // Create file
            String userFile = ResourceManager.getUserPath(userID)+"/"+userID+"_postSurvey.txt";
            FileWriter fstream = new FileWriter(userFile);
            BufferedWriter out = new BufferedWriter(fstream);
            HashMap pageSubmit = (HashMap)submit.get("102");
            if (pageSubmit != null) {
                int i = 0;
                for (Object key : pageSubmit.keySet()) {
                     //System.out.println("test " + (i+1) + key +"="+pageSubmit.get(key)+"\n");
                     i++;
                     out.write(key +":"+pageSubmit.get(key) +"\n");
                     if (i % 2 == 0)
                         out.write("\n");
                }
            }
            out.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }

    private static void saveSurvey(Lesson lesson) {
        //get lesson submission
        HashMap submit = lesson.getModel().getPageSubmit();
        String userID = (String) ((HashMap)submit.get("1")).get("userID");
        try {
            // Create file
            String userFile = ResourceManager.getUserPath(userID)+"/"+userID+"_infor.csv";
            FileWriter fstream = new FileWriter(userFile);
            BufferedWriter out = new BufferedWriter(fstream);
            HashMap pageSubmit = (HashMap)submit.get("101");
            if (pageSubmit != null) {
                int i = 0;
                String row = "";
                for (Object key : pageSubmit.keySet()) {
                     row += "\"" + key + "\"";
                     i++;
                     if (i > 1 && i < pageSubmit.size()) {
                         row = row + ",";
                     }
                }
                row = row + "\n";
                System.out.println(row);
                out.write(row);

                row = "";
                i=0;
                for (Object key : pageSubmit.keySet()) {
                     row += "\"" + pageSubmit.get(key) + "\"";
                     if (i > 1 && i < pageSubmit.size()) {
                         row = row + ",";
                     }
                     i++;
                }
                row = row + "\n";
                System.out.println(row);
                out.write(row);
            }
            out.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }
}
