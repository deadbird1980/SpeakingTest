package com.core.test;

import com.core.lesson.*;
import com.core.util.*;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class ApplicationJSON {
    private JSONObject json;
    public ApplicationJSON(JSONObject obj) {
        json = obj;
    }
    public int getTestsCount() {
        try {
            JSONArray pages = json.getJSONArray("pages");
            int totalTest = 0;
            for(int i=0; i<pages.length(); i++) {
                JSONObject page = pages.getJSONObject(i);
                String type = page.getString("type");
                if (type.equals("SpeakingQuestionPage")) {
                    totalTest++;
                }
            }
            return totalTest;
        } catch (JSONException e) {}
        return 0;
    }

    public int getPageByType(String pageType) {
        try {
            JSONArray pages = json.getJSONArray("pages");
            int totalTest = 0;
            for(int i=0; i<pages.length(); i++) {
                JSONObject page = pages.getJSONObject(i);
                String type = page.getString("type");
                if (type.equals(pageType)) {
                    return i+1;
                }
            }
        } catch (JSONException e) {}
        return -1;
    }

    public JSONArray getPages() {
        try {
            JSONArray pages = json.getJSONArray("pages");
            return pages;
        } catch (JSONException e) {}
        return null;
    }

    public int getPageCountByType(String pageType) {
        try {
            JSONArray pages = json.getJSONArray("pages");
            int totalTest = 0;
            int j = 0;
            for(int i=0; i<pages.length(); i++) {
                JSONObject page = pages.getJSONObject(i);
                String type = page.getString("type");
                if (type.equals(pageType)) {
                    j++;
                }
            }
            return j;
        } catch (JSONException e) {}
        return 0;
    }
    public int[] getPagesByType(String pageType) {
        int[] ids = new int[getPageCountByType(pageType)];
        try {
            JSONArray pages = json.getJSONArray("pages");
            int totalTest = 0;
            int j = 0;
            for(int i=0; i<pages.length(); i++) {
                JSONObject page = pages.getJSONObject(i);
                String type = page.getString("type");
                if (type.equals(pageType)) {
                    ids[j] = i+1;
                    j++;
                }
            }
            return ids;
        } catch (JSONException e) {}
        return null;
    }

    public String getTitle() {
        try {
            String title = json.getString("title");
            return title;
        } catch (JSONException e) {}
        return "";
    }

    public Boolean getFullScreen() {
        try {
            return json.getBoolean("fullScreen");
        } catch (JSONException e) {}
            return false;
    }
}
