package classes;

import classes.helpers.PrintOut;

import java.time.LocalDate;

/**
 * Represents an employee who receives a base salary plus a commission on their sales.
 * This class extends {@link ComissionEmployee} and adds a base salary component to the employee's earnings.
 */
public class BasePlusCommissionEmployee extends ComissionEmployee {

    /** The base salary of the employee. */
    private final double baseSalary;

    /**
     * Constructs a new BasePlusCommissionEmployee with the specified details.
     *
     * @param _firstName           The first name of the employee.
     * @param _lastName            The last name of the employee.
     * @param _socialSecurityNumber The social security number of the employee.
     * @param _grossSale           The gross sales amount.
     * @param _commissionRate      The commission rate.
     * @param _baseSalary          The base salary of the employee.
     */
    public BasePlusCommissionEmployee(String _firstName, String _lastName, String _socialSecurityNumber, double _grossSale,
                                      double _commissionRate, double _baseSalary){
        super( _firstName,  _lastName,  _socialSecurityNumber,  _grossSale,
                _commissionRate);
        this.baseSalary = _baseSalary;
    }

    /**
     * Retrieves the base salary of the employee.
     *
     * @return The base salary.
     */
    public double getBaseSalary(){
        return this.baseSalary;
    }

    /**
     * Calculates the total payment amount for the employee, which is the sum of the base salary and the commission.
     *
     * @return The total payment amount.
     */
    @Override
    public double getPaymentAmount(){
        return this.baseSalary + (this.getGrossSale() * this.getCommissionRate());
    }

    /**
     * Writes the employee's pay stub information to a file.
     * The file includes the employee's name, pay type, base salary, gross sales, commission rate, total payment, and the current date.
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
                    "Employee Pay Type: SALARY - COMMISSION\n" +
                    "--\n" +
                    "Base Salary: $" + String.format("%.2f", this.baseSalary) + "\n" +
                    "Gross Sale: $" + String.format("%.2f", this.getGrossSale()) + "\n" +
                    "Commission Rate: " + String.format("%.2f", this.getCommissionRate()) + "%\n" +
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