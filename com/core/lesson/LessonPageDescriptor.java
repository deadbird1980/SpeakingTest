package com.core.lesson;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;


/**
 * A base descriptor class used to reference a Component page for the Lesson, as
 * well as provide general rules as to how the page should behave.
 */
public class LessonPageDescriptor {

    private static final String DEFAULT_PAGE_IDENTIFIER = "defaultPageIdentifier";
    private String nextID = null;


    /**
     * Identifier returned by getNextPageDescriptor() to indicate that this is the
     * last page and the text of the 'Next' button should change to 'Finish'.
     */
    public static final FinishIdentifier FINISH = new FinishIdentifier();

    public static String ID;

    private Lesson lesson;
    private Component targetPage;
    private Object pageIdentifier;

    /**
     * Default constructor. The id and the Component page must be set separately.
     */
    public LessonPageDescriptor() {
        pageIdentifier = DEFAULT_PAGE_IDENTIFIER;
        targetPage = new LessonPage();
        ((LessonPage)targetPage).setListener(this);
    }

    /**
     * Constructor which accepts both the Object-based identifier and a reference to
     * the Component class which makes up the page.
     * @param id Object-based identifier
     * @param page A class which extends java.awt.Component that will be inserted as a
     * page into the lesson dialog.
     */
    public LessonPageDescriptor(Object id, Component page) {
        pageIdentifier = id;
        targetPage = page;
        ((LessonPage)targetPage).setListener(this);
    }

    /**
     * Returns to java.awt.Component that serves as the actual page.
     * @return A reference to the java.awt.Component that serves as the page
     */
    public final Component getPageComponent() {
        return targetPage;
    }

    /**
     * Sets the page's component as a class that extends java.awt.Component
     * @param page java.awt.Component which serves as the lesson page
     */
    public final void setPageComponent(Component page) {
        targetPage = page;
    }

    /**
     * Returns the unique Object-based identifier for this page descriptor.
     * @return The Object-based identifier
     */
    public final Object getPageDescriptorIdentifier() {
        return pageIdentifier;
    }

    /**
     * Sets the Object-based identifier for this page. The identifier must be unique
     * from all the other identifiers in the page.
     * @param id Object-based identifier for this page.
     */
    public final void setPageDescriptorIdentifier(Object id) {
        pageIdentifier = id;
    }

    final void setLesson(Lesson w) {
        lesson = w;
    }

    /**
     * Returns a reference to the Lesson component.
     * @return The Lesson class hosting this descriptor.
     */
    public final Lesson getLesson() {
        return lesson;
    }

    /**
     * Returns a reference to the current LessonModel for this Lesson component.
     * @return The current LessonModel for this Lesson component.
     */
    public LessonModel getLessonModel() {
        return lesson.getModel();
    }

    //  Override this method to provide an Object-based identifier
    //  for the next page.

    /**
     * Override this class to provide the Object-based identifier of the page that the
     * user should traverse to when the Next button is pressed. Note that this method
     * is only called when the button is actually pressed, so that the page can change
     * the next page's identifier dynamically at runtime if necessary. Return null if
     * the button should be disabled. Return FinishIdentfier if the button text
     * should change to 'Finish' and the dialog should end.
     * @return Object-based identifier.
     */
    public Object getNextPageDescriptor() {
        return nextID;
    }

    /**
     * Override this class to provide the Object-based identifier of the page that the
     * user should traverse to when the Next button is pressed. Note that this method
     * is only called when the button is actually pressed, so that the page can change
     * the next page's identifier dynamically at runtime if necessary. Return null if
     * the button should be disabled. Return FinishIdentfier if the button text
     * should change to 'Finish' and the dialog should end.
     * @return Object-based identifier.
     */
    public void setNextPageDescriptor(String ID) {
        nextID = ID;
    }

    //  Override this method to provide an Object-based identifier
    //  for the previous page.

    /**
     * Override this class to provide the Object-based identifier of the page that the
     * user should traverse to when the Back button is pressed. Note that this method
     * is only called when the button is actually pressed, so that the page can change
     * the previous page's identifier dynamically at runtime if necessary. Return null if
     * the button should be disabled.
     * @return Object-based identifier
     */
    public Object getBackPageDescriptor() {
        return null;
    }

    //  Override this method in the subclass if you wish it to be called
    //  just before the page is displayed.

    /**
     * Override this method to provide functionality that will be performed just before
     * the page is to be displayed.
     */
    public void aboutToDisplayPage() {

    }
 
    //  Override this method in the subclass if you wish to do something
    //  while the page is displaying.

    /**
     * Override this method to perform functionality when the page itself is displayed.
     */
    public void displayingPage() {

    }
 
    //  Override this method in the subclass if you wish it to be called
    //  just before the page is switched to another or finished.

    /**
     * Override this method to perform functionality just before the page is to be
     * hidden.
     */
    public void aboutToHidePage() {

    }

    /**
     * Override this method to perform functionality just before the page is to be
     * hidden.
     */
    public void notifyMessage(String msg) {
    }


    static class FinishIdentifier {
        public static final String ID = "FINISH";
    }
}
