package com.core.util;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.text.PlainDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;

class FixedLengthPlainDocument extends PlainDocument {
    private int maxLength;
    public FixedLengthPlainDocument(int maxLength) {
        this.maxLength = maxLength;
    }
    public void insertString(int offset, String str, AttributeSet a)
                            throws BadLocationException {
        if(getLength() + str.length() > maxLength) {

                Toolkit.getDefaultToolkit().beep();
        }
        else {
                 super.insertString(offset, str, a);

        }
    }
}
public class FixedLengthTextArea extends JTextArea {
    public FixedLengthTextArea(int length) {
        super(new FixedLengthPlainDocument(length));
    }

    public FixedLengthTextArea(int length, int row, int col) {
        super(new FixedLengthPlainDocument(length), "", row, col);
    }
}

