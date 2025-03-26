package classes.helpers;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * A custom {@link DocumentFilter} that limits the number of characters that can be entered into a text component.
 * It also converts the input to uppercase.
 */
public class CharacterLimiter extends DocumentFilter {

    /** The maximum number of characters allowed. */
    private final int maxLength;

    /**
     * Constructs a new CharacterLimiter with the specified maximum length.
     *
     * @param maxLength The maximum number of characters allowed.
     */
    public CharacterLimiter(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Constructs a new CharacterLimiter with a default maximum length of 15 characters.
     */
    public CharacterLimiter() {
        this.maxLength = 15; // Default length
    }

    /**
     * Inserts a string into the document, limiting the total length and converting the string to uppercase.
     *
     * @param fb     The filter bypass used to invoke the default implementation.
     * @param offset The offset in the document where the string should be inserted.
     * @param string The string to insert.
     * @param attr   The attributes to associate with the inserted string.
     * @throws BadLocationException If the offset is invalid.
     */
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
            super.insertString(fb, offset, string.toUpperCase(), attr);
        }
    }

    /**
     * Replaces a range of characters in the document with a new string, limiting the total length and converting the string to uppercase.
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
        if ((fb.getDocument().getLength() - length + text.length()) <= maxLength) {
            super.replace(fb, offset, length, text.toUpperCase(), attrs);
        }
    }
}