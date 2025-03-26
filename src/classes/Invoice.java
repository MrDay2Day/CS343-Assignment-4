package classes;

import classes.helpers.PrintOut;
import classes.interfaceClasses.Payable;

import java.time.LocalDate;

/**
 * Represents an invoice for a specific part, including details like part number, description, quantity, and price.
 * This class implements the {@link Payable} interface, allowing it to calculate and provide payment information.
 */
public class Invoice implements Payable {

    /** The part number of the item in the invoice. */
    private final String partNumber;
    /** The description of the part. */
    private final String partDescription;
    /** The quantity of the part purchased. */
    private final int quantity;
    /** The price per unit of the part. */
    private final double pricePerItem;

    /**
     * Constructs a new Invoice with the specified details.
     *
     * @param _partNumber      The part number.
     * @param _partDescription The part description.
     * @param _quantity        The quantity of the part.
     * @param _pricePerItem    The price per unit of the part.
     */
    public Invoice(String _partNumber ,String _partDescription, int _quantity, double _pricePerItem){
        this.partNumber = _partNumber;
        this.partDescription = _partDescription;
        this.quantity = _quantity;
        this.pricePerItem = _pricePerItem;
    }

    /**
     * Retrieves the part number.
     *
     * @return The part number.
     */
    public String getPartNumber(){
        return this.partNumber;
    }

    /**
     * Retrieves the part description.
     *
     * @return The part description.
     */
    public String getPartDescription(){
        return this.partDescription;
    }

    /**
     * Retrieves the quantity of the part.
     *
     * @return The quantity.
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * Retrieves the price per unit of the part.
     *
     * @return The price per item.
     */
    public double getPricePerItem(){
        return this.pricePerItem;
    }

    /**
     * Calculates the total payment amount for the invoice.
     *
     * @return The total payment amount.
     */
    @Override
    public double getPaymentAmount() {
        return this.quantity * this.pricePerItem;
    }

    /**
     * Writes the invoice details to a file.
     * The file includes the invoice number, part number, part description, price per unit, quantity, total payment, and the current date.
     * Uses {@link PrintOut#toFile(String, String)} to write the content to a file.
     * Handles any exceptions that occur during file writing by printing the exception's stack trace.
     */
    @Override
    public void writeToFile(){
        try{
            long invoiceNumber = System.currentTimeMillis();

            String fileName = (this.partNumber + "-" + invoiceNumber + "-Invoice").replaceAll("[^a-zA-Z0-9.-]", "_");
            String content = "--" +
                    "-----                            Invoice                            -----\n" +
                    "Invoice Number: " + invoiceNumber + "\n" +
                    "--\n" +
                    "Part Number: " + this.partNumber + "\n" +
                    "Part Description: " + this.partDescription + "\n" +
                    "--\n" +
                    "Price/Unit: $" + String.format("%.2f", this.pricePerItem) + "\n" +
                    "Quantity: " + this.quantity + "\n" +
                    "--\n" +
                    "Payment: $" + String.format("%.2f", this.getPaymentAmount()) + "\n" +
                    "Date: " + LocalDate.now() + "\n" +
                    "-----                            _______                            -----\n";
            PrintOut.toFile(content, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}