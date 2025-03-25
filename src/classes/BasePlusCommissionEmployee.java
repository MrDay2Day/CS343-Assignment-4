package classes;

import classes.helpers.PrintOut;

import java.time.LocalDate;

public class BasePlusCommissionEmployee extends ComissionEmployee {

    private final double baseSalary;

    public BasePlusCommissionEmployee(String _firstName, String _lastName, String _socialSecurityNumber, double _grossSale,
                                      double _commissionRate, double _baseSalary){
        super( _firstName,  _lastName,  _socialSecurityNumber,  _grossSale,
         _commissionRate);
        this.baseSalary = _baseSalary;
    }

    public double getBaseSalary(){
        return this.baseSalary;
    }

    @Override
    public double getPaymentAmount(){
        return this.baseSalary + (this.getGrossSale() * this.getCommissionRate());
    }
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
                    "Commission Rate: " + String.format("%.2f", this.getCommissionRate()) + "hrs\n" +
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
