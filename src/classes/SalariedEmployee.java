package classes;

import classes.abstractClasses.Employee;
import classes.helpers.PrintOut;

import java.time.LocalDate;

public class SalariedEmployee extends Employee {
    private final double weeklySalary;

    public SalariedEmployee(String _firstName, String _lastName, String _socialSecurityNumber, double _weeklySalary){
        super( _firstName,  _lastName,  _socialSecurityNumber);
        this.weeklySalary = _weeklySalary;
    }

    // Getters
    public double getWeeklySalary(){
        return this.weeklySalary;
    }

    @Override
    public double getPaymentAmount(){
        return this.weeklySalary;
    }
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
