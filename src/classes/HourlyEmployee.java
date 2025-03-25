package classes;

import classes.abstractClasses.Employee;

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
}
