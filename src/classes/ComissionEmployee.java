package classes;

import classes.abstractClasses.Employee;
import classes.helpers.PrintOut;

import java.time.LocalDate;

/**
 * Represents an employee who is paid based on a commission of their gross sales.
 * This class extends {@link Employee} and calculates the payment amount based on sales and commission rate.
 */
public class ComissionEmployee extends Employee {

    /** The total gross sales made by the employee. */
    private final double grossSale;
    /** The commission rate applied to the gross sales. */
    private final double commissionRate;

    /**
     * Constructs a new ComissionEmployee with the specified details.
     *
     * @param _firstName           The first name of the employee.
     * @param _lastName            The last name of the employee.
     * @param _socialSecurityNumber The social security number of the employee.
     * @param _grossSale           The gross sales amount.
     * @param _commissionRate      The commission rate.
     */
    public ComissionEmployee(String _firstName, String _lastName, String _socialSecurityNumber, double _grossSale,
                             double _commissionRate){
        super( _firstName,  _lastName,  _socialSecurityNumber);
        this.grossSale = _grossSale;
        this.commissionRate = _commissionRate;
    }

    /**
     * Retrieves the gross sales made by the employee.
     *
     * @return The gross sales amount.
     */
    public double getGrossSale(){
        return this.grossSale;
    }

    /**
     * Retrieves the commission rate of the employee.
     *
     * @return The commission rate.
     */
    public double getCommissionRate(){
        return this.commissionRate;
    }

    /**
     * Calculates the payment amount for the employee based on gross sales and commission rate.
     *
     * @return The payment amount.
     */
    @Override
    public double getPaymentAmount(){
        return this.grossSale * this.commissionRate;
    }

    /**
     * Writes the employee's pay stub information to a file.
     * The file includes the employee's name, pay type, gross sales, commission rate, total payment, and the current date.
     * Uses {@link PrintOut#toFile(String, String)} to write the content to a file.
     * Handles any exceptions that occur during file writing by printing the exception's stack trace.
     */
    @Override
    public void writeToFile(){
        try{
            String fileName = this.getFirstName() + " " + this.getLastName() + " - PayStub";
            String content = "--" +
                    "-----                           Pay Stub                           -----\n" +
                    "Name: " + this.getFirstName() + " " + this.getLastName() + "\n" +
                    "Employee Pay Type: COMMISSION\n" +
                    "--\n" +
                    "Gross Sale: $" + String.format("%.2f", this.grossSale) + "\n" +
                    "Commission Rate: " + String.format("%.2f", this.commissionRate) + "%\n" +
                    "--\n" +
                    "Payment: $" + String.format("%.2f", this.getPaymentAmount()) + "\n" +
                    "Date: " + LocalDate.now() + "\n" +
                    "-----                           --------                           -----\n";
            PrintOut.toFile(content, fileName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}