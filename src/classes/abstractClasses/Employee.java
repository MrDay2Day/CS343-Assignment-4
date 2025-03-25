package classes.abstractClasses;

import classes.interfaceClasses.Payable;

public abstract class Employee implements Payable {
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;

    public Employee(String _firstName, String _lastName, String _socialSecurityNumber){
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.socialSecurityNumber = _socialSecurityNumber;
    }

    @Override
    public double getPaymentAmount() {
        return 0;
    }

    @Override
    public void writeToFile() {

    }

    // Getters
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getSocialSecurityNumber(){
        return this.socialSecurityNumber;
    }



}
