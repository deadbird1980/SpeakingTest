package com.core.lesson;

import java.beans.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * The model for the Lesson component, which tracks the text, icons, and enabled state
 * of each of the buttons, as well as the current page that is displayed. Note that
 * the model, in its current form, is not intended to be subclassed.
 */


public class LessonModel {

    /**
     * Identification string for the current page.
     */
    public static final String CURRENT_PAGE_DESCRIPTOR_PROPERTY = "currentPageDescriptorProperty";

    /**
     * Property identification String for the Back button's text
     */
    public static final String BACK_BUTTON_TEXT_PROPERTY = "backButtonTextProperty";
    /**
     * Property identification String for the Back button's icon
     */
    public static final String BACK_BUTTON_ICON_PROPERTY = "backButtonIconProperty";
    /**
     * Property identification String for the Back button's enabled state
     */
    public static final String BACK_BUTTON_ENABLED_PROPERTY = "backButtonEnabledProperty";

    /**
     * Property identification String for the Next button's text
     */
    public static final String NEXT_FINISH_BUTTON_TEXT_PROPERTY = "nextButtonTextProperty";
    /**
     * Property identification String for the Next button's icon
     */
    public static final String NEXT_FINISH_BUTTON_ICON_PROPERTY = "nextButtonIconProperty";
    /**
     * Property identification String for the Next button's enabled state
     */
    public static final String NEXT_FINISH_BUTTON_ENABLED_PROPERTY = "nextButtonEnabledProperty";

    private LessonPageDescriptor currentPage;

    private HashMap pageHashmap;

    private HashMap buttonTextHashmap;
    private HashMap buttonIconHashmap;
    private HashMap buttonEnabledHashmap;

    private HashMap pageSubmission;

    private PropertyChangeSupport propertyChangeSupport;


    /**
     * Default constructor.
     */
    public LessonModel() {

        pageHashmap = new HashMap();
        pageSubmission = new HashMap();

        buttonTextHashmap = new HashMap();
        buttonIconHashmap = new HashMap();
        buttonEnabledHashmap = new HashMap();

        propertyChangeSupport = new PropertyChangeSupport(this);

    }

    /**
     * Returns the currently displayed LessonPageDescriptor.
     * @return The currently displayed LessonPageDescriptor
     */
    LessonPageDescriptor getCurrentPageDescriptor() {
        return currentPage;
    }

    /**
     * Registers the LessonPageDescriptor in the model using the Object-identifier specified.
     * @param id Object-based identifier
     * @param descriptor LessonPageDescriptor that describes the page
     */
    void registerPage(Object id, LessonPageDescriptor descriptor) {

        //  Place a reference to it in a hashtable so we can access it later
        //  when it is about to be displayed.

        pageHashmap.put(id, descriptor);

    }

    /**
     * Sets the current page to that identified by the Object passed in.
     * @param id Object-based page identifier
     * @return boolean indicating success or failure
     */
     boolean setCurrentPage(Object id) {

        //  First, get the hashtable reference to the page that should
        //  be displayed.

        LessonPageDescriptor nextPage =
            (LessonPageDescriptor)pageHashmap.get(id);

        //  If we couldn't find the page that should be displayed, return
        //  false.

        if (nextPage == null)
            throw new LessonPageNotFoundException();  

        LessonPageDescriptor oldPage = currentPage;
        currentPage = nextPage;

        if (oldPage != currentPage)
            firePropertyChange(CURRENT_PAGE_DESCRIPTOR_PROPERTY, oldPage, currentPage);

        return true;

    }

    Object getBackButtonText() {
        return buttonTextHashmap.get(BACK_BUTTON_TEXT_PROPERTY);
    }

    void setBackButtonText(Object newText) {

        Object oldText = getBackButtonText();   
        if (!newText.equals(oldText)) {
            buttonTextHashmap.put(BACK_BUTTON_TEXT_PROPERTY, newText);
            firePropertyChange(BACK_BUTTON_TEXT_PROPERTY, oldText, newText);
        }
    }

    Object getNextFinishButtonText() {
        return buttonTextHashmap.get(NEXT_FINISH_BUTTON_TEXT_PROPERTY);
    }

    void setNextFinishButtonText(Object newText) {

        Object oldText = getNextFinishButtonText();   
        if (!newText.equals(oldText)) {
            buttonTextHashmap.put(NEXT_FINISH_BUTTON_TEXT_PROPERTY, newText);
            firePropertyChange(NEXT_FINISH_BUTTON_TEXT_PROPERTY, oldText, newText);
        }
    }

    Icon getBackButtonIcon() {
        return (Icon)buttonIconHashmap.get(BACK_BUTTON_ICON_PROPERTY);
    }

    void setBackButtonIcon(Icon newIcon) {

        Object oldIcon = getBackButtonIcon();   
        if (!newIcon.equals(oldIcon)) {
            buttonIconHashmap.put(BACK_BUTTON_ICON_PROPERTY, newIcon);
            firePropertyChange(BACK_BUTTON_ICON_PROPERTY, oldIcon, newIcon);
        }
    }

    Icon getNextFinishButtonIcon() {
        return (Icon)buttonIconHashmap.get(NEXT_FINISH_BUTTON_ICON_PROPERTY);
    }

    public void setNextFinishButtonIcon(Icon newIcon) {

        Object oldIcon = getNextFinishButtonIcon();   
        if (!newIcon.equals(oldIcon)) {
            buttonIconHashmap.put(NEXT_FINISH_BUTTON_ICON_PROPERTY, newIcon);
            firePropertyChange(NEXT_FINISH_BUTTON_ICON_PROPERTY, oldIcon, newIcon);
        }
    }


    Boolean getBackButtonEnabled() {
        return (Boolean)buttonEnabledHashmap.get(BACK_BUTTON_ENABLED_PROPERTY);
    }

    void setBackButtonEnabled(Boolean newValue) {

        Boolean oldValue = getBackButtonEnabled();   
        if (newValue != oldValue) {
            buttonEnabledHashmap.put(BACK_BUTTON_ENABLED_PROPERTY, newValue);
            firePropertyChange(BACK_BUTTON_ENABLED_PROPERTY, oldValue, newValue);
        }
    }

    Boolean getNextFinishButtonEnabled() {
        return (Boolean)buttonEnabledHashmap.get(NEXT_FINISH_BUTTON_ENABLED_PROPERTY);
    }

    public void setNextFinishButtonEnabled(Boolean newValue) {

        Boolean oldValue = getNextFinishButtonEnabled();   
        if (newValue != oldValue) {
            buttonEnabledHashmap.put(NEXT_FINISH_BUTTON_ENABLED_PROPERTY, newValue);
            firePropertyChange(NEXT_FINISH_BUTTON_ENABLED_PROPERTY, oldValue, newValue);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener p) {
        propertyChangeSupport.addPropertyChangeListener(p);
    }

    public void removePropertyChangeListener(PropertyChangeListener p) {
        propertyChangeSupport.removePropertyChangeListener(p);
    }

    public void setPageSubmit(String ID, HashMap submit) {
        System.out.println ("set page submit "+ID);
        pageSubmission.put(ID, submit);
    }

    public HashMap getPageSubmit() {
        for (Object key : pageHashmap.keySet()) {
            LessonPageDescriptor descriptor = (LessonPageDescriptor)pageHashmap.get(key);
            HashMap submit = ((LessonPage)descriptor.getPageComponent()).getSubmit();
            if (submit != null) {
                pageSubmission.put(key, submit);
            }
        }
        return pageSubmission;
    }

    public HashMap getSubmit(Object key) {
        LessonPageDescriptor descriptor = (LessonPageDescriptor)pageHashmap.get(key);
        HashMap submit = ((LessonPage)descriptor.getPageComponent()).getSubmit();
        return submit;
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

}
