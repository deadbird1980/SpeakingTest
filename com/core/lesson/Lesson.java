package com.core.lesson;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import java.net.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * This class implements a basic Lesson dialog, where the programmer can
 * insert one or more Components to act as panels. These panels can be navigated
 * through arbitrarily using the 'Next' or 'Back' buttons, or the dialog itself
 * can be closed using the 'Finish' button. Note that even though the dialog
 * uses a CardLayout manager, the order of the panels is not linear. Each panel
 * determines at runtime what its next and previous panel will be.
 */
public class Lesson extends WindowAdapter implements PropertyChangeListener {

    /**
     * Indicates that the 'Finish' button was pressed to close the dialog.
     */
    public static final int FINISH_RETURN_CODE = 0;
    /**
     * Indicates that the dialog closed due to an internal error.
     */
    public static final int ERROR_RETURN_CODE = 1;

    /**
     * The String-based action command for the 'Next' button.
     */
    public static final String NEXT_BUTTON_ACTION_COMMAND = "NextButtonActionCommand";
    /**
     * The String-based action command for the 'Back' button.
     */
    public static final String BACK_BUTTON_ACTION_COMMAND = "BackButtonActionCommand";

    // The i18n text used for the buttons. Loaded from a property resource file.

    static String BACK_TEXT;
    static String NEXT_TEXT;
    static String FINISH_TEXT;

    // The image icons used for the buttons. Filenames are loaded from a property resource file.

    static Icon BACK_ICON;
    static Icon NEXT_ICON;
    static Icon FINISH_ICON;


    private LessonModel LessonModel;
    private LessonController LessonController;
    private JDialog LessonDialog;

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JButton backButton;
    private JButton nextButton;

    private int returnCode;



    /**
     * Default constructor. This method creates a new LessonModel object and passes it
     * into the overloaded constructor.
     */
    public Lesson() {
        this((Frame)null);
    }

    /**
     * This method accepts a java.awt.Dialog object as the javax.swing.JDialog's
     * parent.
     * @param owner The java.awt.Dialog object that is the owner of this dialog.
     */
    public Lesson(Dialog owner) {
        LessonModel = new LessonModel();
        LessonDialog = new JDialog(owner);
        initComponents();
    }

    /**
     * This method accepts a java.awt.Frame object as the javax.swing.JDialog's
     * parent.
     * @param owner The java.awt.Frame object that is the owner of the javax.swing.JDialog.
     */
    public Lesson(Frame owner) {
        LessonModel = new LessonModel();
        LessonDialog = new JDialog(owner);
        initComponents();
    }

    /**
     * Returns an instance of the JDialog that this class created. This is useful in
     * the event that you want to change any of the JDialog parameters manually.
     * @return The JDialog instance that this class created.
     */
    public JDialog getDialog() {
        return LessonDialog;
    }

    /**
     * Returns the owner of the generated javax.swing.JDialog.
     * @return The owner (java.awt.Frame or java.awt.Dialog) of the javax.swing.JDialog generated
     * by this class.
     */
    public Component getOwner() {
        return LessonDialog.getOwner();
    }

    /**
     * Sets the title of the generated javax.swing.JDialog.
     * @param s The title of the dialog.
     */
    public void setTitle(String s) {
        LessonDialog.setTitle(s);
    }

    /**
     * Returns the current title of the generated dialog.
     * @return The String-based title of the generated dialog.
     */
    public String getTitle() {
        return LessonDialog.getTitle();
    }

    /**
     * Sets the modality of the generated javax.swing.JDialog.
     * @param b the modality of the dialog
     */
    public void setModal(boolean b) {
        LessonDialog.setModal(b);
    }

    /**
     * Returns the modality of the dialog.
     * @return A boolean indicating whether or not the generated javax.swing.JDialog is modal.
     */
    public boolean isModal() {
        return LessonDialog.isModal();
    }

    /**
     * Convienence method that displays a modal Lesson dialog and blocks until the dialog
     * has completed.
     * @return Indicates how the dialog was closed. Compare this value against the RETURN_CODE
     * constants at the beginning of the class.
     */
    public int showModalDialog(Boolean fullScreen) {

        LessonDialog.setModal(true);
        if (fullScreen) {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            if (gs.isFullScreenSupported()) {
                // Full-screen mode is supported
            } else {
                // Full-screen mode will be simulated
            }

            try {
                // Enter full-screen mode
                gs.setFullScreenWindow(LessonDialog);
                LessonDialog.validate();
            } finally {
                // Exit full-screen mode
                gs.setFullScreenWindow(null);
            }
        }
        LessonDialog.pack();
        LessonDialog.show();

        return returnCode;
    }

    /**
     * Returns the current model of the Lesson dialog.
     * @return A LessonModel instance, which serves as the model for the Lesson dialog.
     */
    public LessonModel getModel() {
        return LessonModel;
    }

    /**
     * Add a Component as a panel for the Lesson dialog by registering its
     * LessonPanelDescriptor object. Each panel is identified by a unique Object-based
     * identifier (often a String), which can be used by the setCurrentPage()
     * method to display the panel at runtime.
     * @param id An Object-based identifier used to identify the LessonPanelDescriptor object.
     * @param panel The LessonPanelDescriptor object which contains helpful information about the panel.
     */
    public void registerLessonPage(Object id, LessonPageDescriptor panel) {

        //  Add the incoming panel to our JPanel display that is managed by
        //  the CardLayout layout manager.

        cardPanel.add(panel.getPageComponent(), id);

        //  Set a callback to the current Lesson.

        panel.setLesson(this);

        //  Place a reference to it in the model.

        LessonModel.registerPage(id, panel);

    }

    /**
     * Displays the panel identified by the object passed in. This is the same Object-based
     * identified used when registering the panel.
     * @param id The Object-based identifier of the panel to be displayed.
     */
    public void setCurrentPage(Object id) {

        //  Get the hashtable reference to the panel that should
        //  be displayed. If the identifier passed in is null, then close
        //  the dialog.

        if (id == null)
            close(ERROR_RETURN_CODE);

        LessonPageDescriptor oldPageDescriptor = LessonModel.getCurrentPageDescriptor();
        if (oldPageDescriptor != null)
            oldPageDescriptor.aboutToHidePage();

        LessonModel.setCurrentPage(id);
        LessonModel.getCurrentPageDescriptor().aboutToDisplayPage();

        //  Show the panel in the dialog.

        cardLayout.show(cardPanel, id.toString());
        LessonModel.getCurrentPageDescriptor().displayingPage();
    }

    /**
     * Method used to listen for property change events from the model and update the
     * dialog's graphical components as necessary.
     * @param evt PropertyChangeEvent passed from the model to signal that one of its properties has changed value.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(LessonModel.CURRENT_PAGE_DESCRIPTOR_PROPERTY)) {
            LessonController.resetButtonsToPageRules();
        } else if (evt.getPropertyName().equals(LessonModel.NEXT_FINISH_BUTTON_TEXT_PROPERTY)) {
            nextButton.setText(evt.getNewValue().toString());
        } else if (evt.getPropertyName().equals(LessonModel.BACK_BUTTON_TEXT_PROPERTY)) {
            backButton.setText(evt.getNewValue().toString());
        } else if (evt.getPropertyName().equals(LessonModel.NEXT_FINISH_BUTTON_ENABLED_PROPERTY)) {
            nextButton.setEnabled(((Boolean)evt.getNewValue()).booleanValue());
        } else if (evt.getPropertyName().equals(LessonModel.BACK_BUTTON_ENABLED_PROPERTY)) {
            backButton.setEnabled(((Boolean)evt.getNewValue()).booleanValue());
        } else if (evt.getPropertyName().equals(LessonModel.NEXT_FINISH_BUTTON_ICON_PROPERTY)) {
            nextButton.setIcon((Icon)evt.getNewValue());
        } else if (evt.getPropertyName().equals(LessonModel.BACK_BUTTON_ICON_PROPERTY)) {
            backButton.setIcon((Icon)evt.getNewValue());
        }
    }

    /**
     * Retrieves the last return code set by the dialog.
     * @return An integer that identifies how the dialog was closed. See the *_RETURN_CODE
     * constants of this class for possible values.
     */
    public int getReturnCode() {
        return returnCode;
    }

   /**
     * Mirrors the LessonModel method of the same name.
     * @return A boolean indicating if the button is enabled.
     */
    public boolean getBackButtonEnabled() {
        return LessonModel.getBackButtonEnabled().booleanValue();
    }


   /**
     * Mirrors the LessonModel method of the same name.
     * @param boolean newValue The new enabled status of the button.
     */
    public void setBackButtonEnabled(boolean newValue) {
        LessonModel.setBackButtonEnabled(new Boolean(newValue));
    }

   /**
     * Mirrors the LessonModel method of the same name.
     * @return A boolean indicating if the button is enabled.
     */
    public boolean getNextFinishButtonEnabled() {
        return LessonModel.getNextFinishButtonEnabled().booleanValue();
    }

   /**
     * Mirrors the LessonModel method of the same name.
     * @param boolean newValue The new enabled status of the button.
     */
    public void setNextFinishButtonEnabled(boolean newValue) {
        LessonModel.setNextFinishButtonEnabled(new Boolean(newValue));
    }


    /**
     * Closes the dialog and sets the return code to the integer parameter.
     * @param code The return code.
     */
    void close(int code) {
        returnCode = code;
        LessonDialog.dispose();
    }

    /**
     * This method initializes the components for the Lesson dialog: it creates a JDialog
     * as a CardLayout panel surrounded by a small amount of space on each side, as well
     * as three buttons at the bottom.
     */

    private void initComponents() {

        LessonModel.addPropertyChangeListener(this);
        LessonController = new LessonController(this);

        LessonDialog.getContentPane().setLayout(new BorderLayout());
        LessonDialog.addWindowListener(this);

        //  Create the outer Lesson panel, which is responsible for three buttons:
        //  Next, Back. It is also responsible a JPanel above them that
        //  uses a CardLayout layout manager to display multiple panels in the
        //  same spot.

        JPanel buttonPanel = new JPanel();
        JSeparator separator = new JSeparator();
        Box buttonBox = new Box(BoxLayout.X_AXIS);

        cardPanel = new JPanel();
        cardPanel.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));  

        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        backButton = new JButton(new ImageIcon("com/core/Lesson/backIcon.gif"));
        nextButton = new JButton();

        backButton.setActionCommand(BACK_BUTTON_ACTION_COMMAND);
        nextButton.setActionCommand(NEXT_BUTTON_ACTION_COMMAND);

        backButton.addActionListener(LessonController);
        nextButton.addActionListener(LessonController);

        //  Create the buttons with a separator above them, then place them
        //  on the east side of the panel with a small amount of space between
        //  the back and the next button, and a larger amount of space between
        //  the next button

        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(separator, BorderLayout.NORTH);

        buttonBox.setBorder(new EmptyBorder(new Insets(5, 10, 5, 10)));  
        //buttonBox.add(backButton);
        buttonBox.add(Box.createHorizontalStrut(10));
        buttonBox.add(nextButton);
        buttonBox.add(Box.createHorizontalStrut(30));

        buttonPanel.add(buttonBox, java.awt.BorderLayout.EAST);

        LessonDialog.getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);
        LessonDialog.getContentPane().add(cardPanel, java.awt.BorderLayout.CENTER);

    }

    private static Object getImage(String name) {

        URL url = null;

        try {
            Class c = Class.forName("com.core.lesson.Lesson");
            url = c.getResource(name);
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Unable to find Lesson class");
        }
        return url;

    }




    static {

        try {

            PropertyResourceBundle resources = (PropertyResourceBundle)
                ResourceBundle.getBundle("com.core.lesson.lesson");

            BACK_TEXT = (String)(resources.getObject("backButtonText"));
            NEXT_TEXT = (String)(resources.getObject("nextButtonText"));
            FINISH_TEXT = (String)(resources.getObject("finishButtonText"));

            BACK_ICON = new ImageIcon((URL)getImage((String)(resources.getObject("backButtonIcon"))));
            NEXT_ICON = new ImageIcon((URL)getImage((String)(resources.getObject("nextButtonIcon"))));
            FINISH_ICON = new ImageIcon((URL)getImage((String)(resources.getObject("finishButtonIcon"))));
        } catch (MissingResourceException mre) {
            System.out.println(mre);
            System.exit(1);
        }
    }

}
