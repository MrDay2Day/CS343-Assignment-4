package classes;

import classes.interfaceClasses.Payable;

public class Invoice implements Payable {
    private final String partNumber;
    private final String partDescription;
    private final int quantity;
    private final double pricePerItem;

    Invoice(String _partNumber ,String _partDescription, int _quantity, double _pricePerItem){
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
        return 0;
    }
    @Override
    public void writeToFile() {

    }
}
