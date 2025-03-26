package classes;

import classes.abstractClasses.Employee;
import classes.helpers.PrintOut;

import java.time.LocalDate;

/**
 * Represents an employee who is paid a fixed weekly salary.
 * This class extends {@link Employee} and calculates the payment amount based on the weekly salary.
 */
public class SalariedEmployee extends Employee {

    /** The weekly salary of the employee. */
    private final double weeklySalary;

    /**
     * Constructs a new SalariedEmployee with the specified details.
     *
     * @param _firstName           The first name of the employee.
     * @param _lastName            The last name of the employee.
     * @param _socialSecurityNumber The social security number of the employee.
     * @param _weeklySalary        The weekly salary.
     */
    public SalariedEmployee(String _firstName, String _lastName, String _socialSecurityNumber, double _weeklySalary){
        super( _firstName,  _lastName,  _socialSecurityNumber);
        this.weeklySalary = _weeklySalary;
    }

    /**
     * Retrieves the weekly salary of the employee.
     *
     * @return The weekly salary.
     */
    public double getWeeklySalary(){
        return this.weeklySalary;
    }

    /**
     * Calculates the payment amount for the employee, which is the weekly salary.
     *
     * @return The payment amount.
     */
    @Override
    public double getPaymentAmount(){
        return this.weeklySalary;
    }

    /**
     * Writes the employee's pay stub information to a file.
     * The file includes the employee's name, pay type, weekly salary, total payment, and the current date.
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
                    "Employee Pay Type: WEEKLY\n" +
                    "--\n" +
                    "Weekly Salary: $" + String.format("%.2f", this.weeklySalary) + "\n" +
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