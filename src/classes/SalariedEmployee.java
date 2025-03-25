package classes;

import classes.abstractClasses.Employee;

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
}
