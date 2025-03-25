package classes;

import classes.abstractClasses.Employee;

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
}
