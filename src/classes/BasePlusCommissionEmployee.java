package classes;

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

}
