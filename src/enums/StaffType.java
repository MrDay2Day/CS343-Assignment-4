package enums;

public enum StaffType {
    SELECT_STAFF_TYPE("Select Staff Type"),
    COMMISSION_EMPLOYEE("Commission Employee"),
    HOURLY_EMPLOYEE("Hourly Employee"),
    SALARIED_EMPLOYEE("Salaried Employee"),
    BASE_PLUS_COMMISSION_EMPLOYEE("Base Plus Commission Employee");

    private final String displayName;

    StaffType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName; // Optional: use display name as default string representation
    }
}