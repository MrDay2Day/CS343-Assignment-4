package enums;

/**
 * Enumeration representing different types of staff.
 * Each enum constant has a display name associated with it, which can be used for UI representation.
 */
public enum StaffType {
    /** Represents the option to select a staff type. */
    SELECT_STAFF_TYPE("Select Staff Type"),
    /** Represents a commission-based employee. */
    COMMISSION_EMPLOYEE("Commission Employee"),
    /** Represents an hourly-paid employee. */
    HOURLY_EMPLOYEE("Hourly Employee"),
    /** Represents a salaried employee. */
    SALARIED_EMPLOYEE("Salaried Employee"),
    /** Represents an employee with a base salary plus commission. */
    BASE_PLUS_COMMISSION_EMPLOYEE("Base Plus Commission Employee");

    /** The display name of the staff type. */
    private final String displayName;

    /**
     * Constructs a new StaffType with the specified display name.
     *
     * @param displayName The display name of the staff type.
     */
    StaffType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Retrieves the display name of the staff type.
     *
     * @return The display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the display name of the staff type.
     *
     * @return The display name.
     */
    @Override
    public String toString() {
        return displayName; // Optional: use display name as default string representation
    }
}