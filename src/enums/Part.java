package enums;

public class Part {
    private final String partNumber;
    private final String description;
    private final double price;

    public Part(String partNumber, String description, double price) {
        this.partNumber = partNumber;
        this.description = description;
        this.price = price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
