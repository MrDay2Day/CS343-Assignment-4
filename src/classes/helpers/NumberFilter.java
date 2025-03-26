package classes.helpers;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * A custom {@link DocumentFilter} that restricts input to decimal numbers and limits the maximum length.
 */
public class NumberFilter extends DocumentFilter {

    /** The maximum number of characters allowed. */
    private final int maxLength;

    /**
     * Constructs a new NumberFilter with the specified maximum length.
     *
     * @param maxLength The maximum number of characters allowed.
     */
    public NumberFilter(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Constructs a new NumberFilter with a default maximum length of 15 characters.
     */
    public NumberFilter() {
        this.maxLength = 15; // Default length
    }

    /**
     * Inserts a string into the document, allowing only decimal numbers and respecting the maximum length.
     *
     * @param fb     The filter bypass used to invoke the default implementation.
     * @param offset The offset in the document where the string should be inserted.
     * @param string The string to insert.
     * @param attr   The attributes to associate with the inserted string.
     * @throws BadLocationException If the offset is invalid.
     */
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string != null && isValidDecimal(fb.getDocument().getText(0, fb.getDocument().getLength()), string)) {
            if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
                super.insertString(fb, offset, string, attr);
            }
        }
    }

    /**
     * Replaces a range of characters in the document with a new string, allowing only decimal numbers and respecting the maximum length.
     *
     * @param fb     The filter bypass used to invoke the default implementation.
     * @param offset The offset in the document where the replacement should start.
     * @param length The length of the range to replace.
     * @param text   The replacement string.
     * @param attrs  The attributes to associate with the replacement string.
     * @throws BadLocationException If the offset or length is invalid.
     */
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isValidDecimal(fb.getDocument().getText(0, fb.getDocument().getLength()), text)) {
            if ((fb.getDocument().getLength() - length + text.length()) <= maxLength) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    /**
     * Checks if the combined text (current text + new text) is a valid decimal number.
     *
     * @param currentText The current text in the document.
     * @param newText     The new text to be inserted or replaced.
     * @return {@code true} if the combined text is a valid decimal, {@code false} otherwise.
     */
    private boolean isValidDecimal(String currentText, String newText) {
        String fullText = currentText + newText;
        return fullText.matches("^\\d*\\.?\\d*$");
    }
}