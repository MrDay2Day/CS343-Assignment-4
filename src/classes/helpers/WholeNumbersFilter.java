package classes.helpers;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * A custom {@link DocumentFilter} that restricts input to whole numbers (integers) and limits the maximum length.
 */
public class WholeNumbersFilter extends DocumentFilter {

    /** The maximum number of characters allowed. */
    private final int maxLength;

    /**
     * Constructs a new WholeNumbersFilter with the specified maximum length.
     *
     * @param maxLength The maximum number of characters allowed.
     */
    public WholeNumbersFilter(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Constructs a new WholeNumbersFilter with a default maximum length of 15 characters.
     */
    public WholeNumbersFilter() {
        this.maxLength = 15; // Default length
    }

    /**
     * Inserts a string into the document, allowing only whole numbers and respecting the maximum length.
     *
     * @param fb     The filter bypass used to invoke the default implementation.
     * @param offset The offset in the document where the string should be inserted.
     * @param string The string to insert.
     * @param attr   The attributes to associate with the inserted string.
     * @throws BadLocationException If the offset is invalid.
     */
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string != null && isValidWholeNumber(string)) {
            if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
                super.insertString(fb, offset, string, attr);
            }
        }
    }

    /**
     * Replaces a range of characters in the document with a new string, allowing only whole numbers and respecting the maximum length.
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
        if (isValidWholeNumber(text)) {
            if ((fb.getDocument().getLength() - length + text.length()) <= maxLength) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    /**
     * Checks if the provided text is a valid whole number (contains only digits).
     *
     * @param text The text to check.
     * @return {@code true} if the text is a valid whole number, {@code false} otherwise.
     */
    private boolean isValidWholeNumber(String text) {
        return text.matches("\\d*"); // Allow only digits (no decimal points or other characters)
    }
}