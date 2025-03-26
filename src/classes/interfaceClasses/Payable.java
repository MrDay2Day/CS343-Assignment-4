package classes.interfaceClasses;

/**
 * Interface representing an object that can provide payment information and write its details to a file.
 */
public interface Payable {

    /**
     * Calculates and retrieves the payment amount.
     *
     * @return The payment amount.
     */
    double getPaymentAmount();

    /**
     * Writes the payment details to a file.
     */
    void writeToFile();
}