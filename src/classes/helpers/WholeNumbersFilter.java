package classes.helpers;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class WholeNumbersFilter extends DocumentFilter {

    private final int maxLength;

    public WholeNumbersFilter(int maxLength) {
        this.maxLength = maxLength;
    }

    public WholeNumbersFilter() {
        this.maxLength = 15; // Default length
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string != null && isValidWholeNumber(string)) {
            if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
                super.insertString(fb, offset, string, attr);
            }
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isValidWholeNumber(text)) {
            if ((fb.getDocument().getLength() - length + text.length()) <= maxLength) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    private boolean isValidWholeNumber(String text) {
        return text.matches("\\d*"); // Allow only digits (no decimal points or other characters)
    }
}