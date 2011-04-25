package com.core.util;


import javax.swing.*;
import java.util.Enumeration;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * The model for the Lesson component, which tracks the text, icons, and enabled state
 * of each of the buttons, as well as the current page that is displayed. Note that
 * the model, in its current form, is not intended to be subclassed.
 */


public class Utils {
    public static JRadioButton getSelection(ButtonGroup group) {
        for (Enumeration e=group.getElements(); e.hasMoreElements(); ) {
           JRadioButton b = (JRadioButton)e.nextElement();
           if (b.getModel() == group.getSelection()) {
               return b;
           }
        }
        return null;
    }
    public static String getToday() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        return df.format(today);
    }
}
