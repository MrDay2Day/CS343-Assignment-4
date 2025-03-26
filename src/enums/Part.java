package enums;

/**
 * Represents a part with a part number, description, and price.
 * This class is used to store and retrieve details about a specific part.
 */
public class Part {
    /** The part number. */
    private final String partNumber;
    /** The description of the part. */
    private final String description;
    /** The price of the part. */
    private final double price;

    /**
     * Constructs a new Part with the specified details.
     *
     * @param partNumber  The part number.
     * @param description The description of the part.
     * @param price       The price of the part.
     */
    public Part(String partNumber, String description, double price) {
        this.partNumber = partNumber;
        this.description = description;
        this.price = price;
    }

    /**
     * Retrieves the part number.
     *
     * @return The part number.
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * Retrieves the description of the part.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the price of the part.
     *
     * @return The price.
     */
    public double getPrice() {
        return price;
    }
}