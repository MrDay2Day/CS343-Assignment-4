package classes.abstractClasses;

import classes.interfaceClasses.Payable;

/**
 * Abstract class representing an employee.
 * This class implements the {@link Payable} interface and provides basic employee information.
 * It serves as a base class for different types of employees with varying payment structures.
 */
public abstract class Employee implements Payable {

    /** The first name of the employee. */
    private final String firstName;
    /** The last name of the employee. */
    private final String lastName;
    /** The social security number of the employee. */
    private final String socialSecurityNumber;

    /**
     * Constructs a new Employee with the specified details.
     *
     * @param _firstName           The first name of the employee.
     * @param _lastName            The last name of the employee.
     * @param _socialSecurityNumber The social security number of the employee.
     */
    public Employee(String _firstName, String _lastName, String _socialSecurityNumber){
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.socialSecurityNumber = _socialSecurityNumber;
    }

    /**
     * Calculates the payment amount for the employee.
     * This method is abstract and should be implemented by subclasses to provide specific payment calculations.
     *
     * @return The payment amount. Defaults to 0 in the base class.
     */
    @Override
    public double getPaymentAmount() {
        return 0;
    }

    /**
     * Writes the employee's payment information to a file.
     * This method is abstract and should be implemented by subclasses to provide specific file writing logic.
     */
    @Override
    public void writeToFile() {
        // Implementation to be provided by subclasses.
    }

    /**
     * Retrieves the first name of the employee.
     *
     * @return The first name.
     */
    public String getFirstName(){
        return this.firstName;
    }

    /**
     * Retrieves the last name of the employee.
     *
     * @return The last name.
     */
    public String getLastName(){
        return this.lastName;
    }

    /**
     * Retrieves the social security number of the employee.
     *
     * @return The social security number.
     */
    public String getSocialSecurityNumber(){
        return this.socialSecurityNumber;
    }
}