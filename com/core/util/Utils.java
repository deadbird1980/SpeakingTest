package com.core.util;


import javax.swing.*;
import java.util.Enumeration;

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
}
