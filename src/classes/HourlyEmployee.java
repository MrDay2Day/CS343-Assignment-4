package classes;

import classes.abstractClasses.Employee;
import classes.helpers.PrintOut;

import java.time.LocalDate;

/**
 * Represents an employee who is paid an hourly wage for the number of hours worked.
 * This class extends {@link Employee} and calculates the payment amount based on hourly wage and hours worked.
 */
public class HourlyEmployee extends Employee {

    /** The hourly wage of the employee. */
    private final double wage;
    /** The number of hours worked by the employee. */
    private final double hours;

    /**
     * Constructs a new HourlyEmployee with the specified details.
     *
     * @param _firstName           The first name of the employee.
     * @param _lastName            The last name of the employee.
     * @param _socialSecurityNumber The social security number of the employee.
     * @param _wage                The hourly wage.
     * @param _hours               The number of hours worked.
     */
    public HourlyEmployee(String _firstName, String _lastName, String _socialSecurityNumber, double _wage,
                          double _hours){
        super( _firstName,  _lastName,  _socialSecurityNumber);
        this.wage = _wage;
        this.hours = _hours;
    }

    /**
     * Retrieves the hourly wage of the employee.
     *
     * @return The hourly wage.
     */
    public double getWage(){
        return this.wage;
    }

    /**
     * Retrieves the number of hours worked by the employee.
     *
     * @return The number of hours worked.
     */
    public double getHours(){
        return this.hours;
    }

    /**
     * Calculates the payment amount for the employee based on hourly wage and hours worked.
     *
     * @return The payment amount.
     */
    @Override
    public double getPaymentAmount(){
        return this.wage * this.hours;
    }

    /**
     * Writes the employee's pay stub information to a file.
     * The file includes the employee's name, pay type, hourly rate, hours worked, total payment, and the current date.
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
                    "Employee Pay Type: HOURLY\n" +
                    "--\n" +
                    "Hourly Rate: $" + String.format("%.2f", this.wage) + "\n" +
                    "Hours Worked: " + String.format("%.2f", this.hours) + "hrs\n" +
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