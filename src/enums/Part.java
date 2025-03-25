package enums;

public class Part {
    private final String partNumber;
    private final String description;
    private final double pricePerItem;

    public Part(String partNumber, String description, double pricePerItem) {
        this.partNumber = partNumber;
        this.description = description;
        this.pricePerItem = pricePerItem;
    }

    public String getDescription() {
        return description;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public String getPartNumber() {
        return partNumber;
    }
}
