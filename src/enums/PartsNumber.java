package enums;

public enum PartsNumber {
    A1(new Part("A000001", "Part 34Ab Fits machine BV34", 34.6)),
    A2(new Part("A000002", "Part 12X Fits machine ZX12", 45.0)),
    A3(new Part("A000003", "Part 89P Fits machine PV89", 29.5)),
    A4(new Part("A000004", "Part 56Q Fits machine QX56", 55.3)),
    A5(new Part("A000005", "Part 78M Fits machine MX78", 40.2)),
    A6(new Part("A000006", "Part 23J Fits machine JX23", 38.9)),
    A7(new Part("A000007", "Part 65R Fits machine RX65", 60.0)),
    A8(new Part("A000008", "Part 44T Fits machine TX44", 32.7)),
    A9(new Part("A000009", "Part 99Z Fits machine ZX99", 70.5)),
    A10(new Part("A000010", "Part 11W Fits machine WX11", 25.4)),
    A11(new Part("A000011", "Part 37D Fits machine DX37", 48.6)),
    A12(new Part("A000012", "Part 42Y Fits machine YX42", 53.2)),
    A13(new Part("A000013", "Part 88K Fits machine KX88", 62.9)),
    A14(new Part("A000014", "Part 74N Fits machine NX74", 36.3)),
    A15(new Part("A000015", "Part 18H Fits machine HX18", 27.8)),
    A16(new Part("A000016", "Part 50F Fits machine FX50", 47.1)),
    A17(new Part("A000017", "Part 93B Fits machine BX93", 64.5)),
    A18(new Part("A000018", "Part 81L Fits machine LX81", 42.6)),
    A19(new Part("A000019", "Part 66V Fits machine VX66", 39.9)),
    A20(new Part("A000020", "Part 30S Fits machine SX30", 49.7));

    private final Part part;

    PartsNumber(Part part) {
        this.part = part;
    }

    public Part getPart() {
        return part;
    }
}

