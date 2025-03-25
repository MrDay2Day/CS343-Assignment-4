package classes;

import classes.abstractClasses.Employee;
import classes.helpers.PrintOut;

import java.time.LocalDate;

public class ComissionEmployee extends Employee {

    private final double grossSale;
    private final double commissionRate;

    public ComissionEmployee(String _firstName, String _lastName, String _socialSecurityNumber, double _grossSale,
                             double _commissionRate){
        super( _firstName,  _lastName,  _socialSecurityNumber);
        this.grossSale = _grossSale;
        this.commissionRate = _commissionRate;

    }

    // Getters
    public double getGrossSale(){
        return this.grossSale;
    }
    public double getCommissionRate(){
        return this.commissionRate;
    }


    @Override
    public double getPaymentAmount(){
        return this.grossSale * this.commissionRate;
    }
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
                    "Commission Rate: " + String.format("%.2f", this.commissionRate) + "hrs\n" +
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
