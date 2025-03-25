package classes;

import classes.helpers.PrintOut;
import classes.interfaceClasses.Payable;

import java.time.LocalDate;

public class Invoice implements Payable {
    private final String partNumber;
    private final String partDescription;
    private final int quantity;
    private final double pricePerItem;

    public Invoice(String _partNumber ,String _partDescription, int _quantity, double _pricePerItem){
        this.partNumber = _partNumber;
        this.partDescription = _partDescription;
        this.quantity = _quantity;
        this.pricePerItem = _pricePerItem;
    }

    // Getters
    public String getPartNumber(){
        return this.partNumber;
    }
    public String getPartDescription(){
        return this.partDescription;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public double getPricePerItem(){
        return this.pricePerItem;
    }

    @Override
    public double getPaymentAmount() {
        return this.quantity * this.pricePerItem;
    }
    @Override
    public void writeToFile(){
        try{
            int invoiceNumber = Integer.parseInt(String.valueOf(System.currentTimeMillis()));
            String fileName = this.partNumber + "-" + invoiceNumber + " - Invoice";
            String content = "--" +
                    "-----                            Invoice                            -----\n" +
                    "Invoice Number: " + invoiceNumber + "\n" +
                    "--\n" +
                    "Part Number: " + this.partNumber + "\n" +
                    "Part Description: " + this.partDescription + "\n" +
                    "--\n" +
                    "Price/Unit: $" + String.format("%.2f", this.pricePerItem) + "\n" +
                    "Quantity: " + String.format("%.2f", this.quantity) + "\n" +
                    "--\n" +
                    "Payment: $" + String.format("%.2f", this.getPaymentAmount()) + "\n" +
                    "Date: " + LocalDate.now() + "\n" +
                    "-----                            _______                            -----\n";
            PrintOut.toFile(content, fileName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
