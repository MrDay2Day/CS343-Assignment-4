package classes.helpers;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CharacterLimiter extends DocumentFilter {

    private final int maxLength;

    public CharacterLimiter(int maxLength) {
        this.maxLength = maxLength;
    }

    public CharacterLimiter() {
        this.maxLength = 15; // Default length
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
                super.insertString(fb, offset, string.toUpperCase(), attr);
            }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if ((fb.getDocument().getLength() - length + text.length()) <= maxLength) {
                super.replace(fb, offset, length, text.toUpperCase(), attrs);
            }
    }
}
