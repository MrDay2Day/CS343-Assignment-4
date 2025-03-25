package classes;

import classes.abstractClasses.Employee;
import classes.helpers.PrintOut;

import java.time.LocalDate;

public class HourlyEmployee  extends Employee {

    private final double wage;
    private final double hours;

    public HourlyEmployee(String _firstName, String _lastName, String _socialSecurityNumber, double _wage,
                             double _hours){
        super( _firstName,  _lastName,  _socialSecurityNumber);
        this.wage = _wage;
        this.hours = _hours;

    }

    public double getWage(){
        return this.wage;
    }
    public double getHours(){
        return this.hours;
    }

    @Override
    public double getPaymentAmount(){
        return this.wage * this.hours;
    }
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
