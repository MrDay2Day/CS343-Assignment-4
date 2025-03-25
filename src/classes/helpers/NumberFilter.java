package classes.helpers;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumberFilter extends DocumentFilter {

    private final int maxLength;

    public NumberFilter(int maxLength) {
        this.maxLength = maxLength;
    }

    public NumberFilter() {
        this.maxLength = 15; // Default length
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string != null && isValidDecimal(fb.getDocument().getText(0, fb.getDocument().getLength()), string)) {
            if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
                super.insertString(fb, offset, string, attr);
            }
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isValidDecimal(fb.getDocument().getText(0, fb.getDocument().getLength()), text)) {
            if ((fb.getDocument().getLength() - length + text.length()) <= maxLength) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    private boolean isValidDecimal(String currentText, String newText) {
        String fullText = currentText + newText;
        return fullText.matches("^\\d*\\.?\\d*$");
    }
}
