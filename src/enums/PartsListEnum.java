package enums;

/**
 * Enumeration representing a list of parts with their corresponding details.
 * Each enum constant holds an instance of the {@link Part} class, which contains the part number, description, and price.
 */
public enum PartsListEnum {
    /** Part A1. */
    A1(new Part("A000001", "Part 34Ab Fits machine BV34", 34.6)),
    /** Part A2. */
    A2(new Part("A000002", "Part 12X Fits machine ZX12", 45.0)),
    /** Part A3. */
    A3(new Part("A000003", "Part 89P Fits machine PV89", 29.5)),
    /** Part A4. */
    A4(new Part("A000004", "Part 56Q Fits machine QX56", 55.3)),
    /** Part A5. */
    A5(new Part("A000005", "Part 78M Fits machine MX78", 40.2)),
    /** Part A6. */
    A6(new Part("A000006", "Part 23J Fits machine JX23", 38.9)),
    /** Part A7. */
    A7(new Part("A000007", "Part 65R Fits machine RX65", 60.0)),
    /** Part A8. */
    A8(new Part("A000008", "Part 44T Fits machine TX44", 32.7)),
    /** Part A9. */
    A9(new Part("A000009", "Part 99Z Fits machine ZX99", 70.5)),
    /** Part A10. */
    A10(new Part("A000010", "Part 11W Fits machine WX11", 25.4)),
    /** Part A11. */
    A11(new Part("A000011", "Part 37D Fits machine DX37", 48.6)),
    /** Part A12. */
    A12(new Part("A000012", "Part 42Y Fits machine YX42", 53.2)),
    /** Part A13. */
    A13(new Part("A000013", "Part 88K Fits machine KX88", 62.9)),
    /** Part A14. */
    A14(new Part("A000014", "Part 74N Fits machine NX74", 36.3)),
    /** Part A15. */
    A15(new Part("A000015", "Part 18H Fits machine HX18", 27.8)),
    /** Part A16. */
    A16(new Part("A000016", "Part 50F Fits machine FX50", 47.1)),
    /** Part A17. */
    A17(new Part("A000017", "Part 93B Fits machine BX93", 64.5)),
    /** Part A18. */
    A18(new Part("A000018", "Part 81L Fits machine LX81", 42.6)),
    /** Part A19. */
    A19(new Part("A000019", "Part 66V Fits machine VX66", 39.9)),
    /** Part A20. */
    A20(new Part("A000020", "Part 30S Fits machine SX30", 49.7));

    /** The Part associated with the enum constant. */
    private final Part part;

    /**
     * Constructs a new PartsListEnum constant with the specified Part.
     *
     * @param part The Part associated with the enum constant.
     */
    PartsListEnum(Part part) {
        this.part = part;
    }

    /**
     * Retrieves the Part associated with the enum constant.
     *
     * @return The Part object.
     */
    public Part getPart() {
        return part;
    }
}