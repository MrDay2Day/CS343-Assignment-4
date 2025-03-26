package classes.helpers;

import classes.*;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Manages persistent data for different types of employees.
 * This class provides methods to add, remove, retrieve, and check employee records based on their social security number (SSN).
 */
public class PersistentData {

    /** List to store commission employees. */
    private ArrayList<ComissionEmployee> commissionEmployeeArray;
    /** List to store base plus commission employees. */
    private ArrayList<BasePlusCommissionEmployee> basePlusCommissionEmployeeArray;
    /** List to store hourly employees. */
    private ArrayList<HourlyEmployee> hourlyEmployeeArray;
    /** List to store salaried employees. */
    private ArrayList<SalariedEmployee> salariedEmployeeArray;

    /**
     * Constructs a new PersistentData object and initializes the employee lists.
     */
    public PersistentData(){
        initialize();
    }

    /**
     * Initializes the employee lists.
     */
    public void initialize(){
        this.commissionEmployeeArray = new ArrayList<>();
        this.basePlusCommissionEmployeeArray = new ArrayList<>();
        this.hourlyEmployeeArray = new ArrayList<>();
        this.salariedEmployeeArray = new ArrayList<>();
    }

    /**
     * Retrieves the list of commission employees.
     *
     * @return The list of commission employees.
     */
    public ArrayList<ComissionEmployee> getCommissionEmployee(){
        return commissionEmployeeArray;
    }

    /**
     * Retrieves the list of base plus commission employees.
     *
     * @return The list of base plus commission employees.
     */
    public ArrayList<BasePlusCommissionEmployee> getBasePlusCommissionEmployee(){
        return basePlusCommissionEmployeeArray;
    }

    /**
     * Retrieves the list of hourly employees.
     *
     * @return The list of hourly employees.
     */
    public ArrayList<HourlyEmployee> getHourlyEmployee(){
        return hourlyEmployeeArray;
    }

    /**
     * Retrieves the list of salaried employees.
     *
     * @return The list of salaried employees.
     */
    public ArrayList<SalariedEmployee> getSalariedEmployee(){
        return salariedEmployeeArray;
    }

    /**
     * Adds a commission employee to the list.
     *
     * @param comissionEmployee The commission employee to add.
     * @throws RuntimeException If an error occurs during the add operation.
     */
    public void addCommissionEmployee(ComissionEmployee comissionEmployee){
        try{
            commissionEmployeeArray.add(comissionEmployee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a base plus commission employee to the list.
     *
     * @param basePlusCommissionEmployee The base plus commission employee to add.
     * @throws RuntimeException If an error occurs during the add operation.
     */
    public void addBasePlusCommissionEmployee(BasePlusCommissionEmployee basePlusCommissionEmployee){
        try{
            basePlusCommissionEmployeeArray.add(basePlusCommissionEmployee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds an hourly employee to the list.
     *
     * @param hourlyEmployee The hourly employee to add.
     * @throws RuntimeException If an error occurs during the add operation.
     */
    public void addHourlyEmployee(HourlyEmployee hourlyEmployee){
        try{
            hourlyEmployeeArray.add(hourlyEmployee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a salaried employee to the list.
     *
     * @param salariedEmployee The salaried employee to add.
     * @throws RuntimeException If an error occurs during the add operation.
     */
    public void addSalariedEmployee(SalariedEmployee salariedEmployee){
        try{
            salariedEmployeeArray.add(salariedEmployee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes a commission employee from the list based on the SSN.
     *
     * @param ssn The social security number of the employee to remove.
     * @throws RuntimeException If an error occurs during the remove operation.
     */
    public void removeFromCommissionEmployee(String ssn){
        try{
            for(int i = 0; i < commissionEmployeeArray.size(); i++){
                if(Objects.equals(commissionEmployeeArray.get(i).getSocialSecurityNumber(), ssn)){
                    commissionEmployeeArray.remove(i);
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes a base plus commission employee from the list based on the SSN.
     *
     * @param ssn The social security number of the employee to remove.
     * @throws RuntimeException If an error occurs during the remove operation.
     */
    public void removeFromBasePlusCommissionEmployee(String ssn){
        try{
            for(int i = 0; i < basePlusCommissionEmployeeArray.size(); i++){
                if(Objects.equals(basePlusCommissionEmployeeArray.get(i).getSocialSecurityNumber(), ssn)){
                    basePlusCommissionEmployeeArray.remove(i);
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes an hourly employee from the list based on the SSN.
     *
     * @param ssn The social security number of the employee to remove.
     * @throws RuntimeException If an error occurs during the remove operation.
     */
    public void removeFromHourlyEmployee(String ssn){
        try{
            for(int i = 0; i < hourlyEmployeeArray.size(); i++){
                if(Objects.equals(hourlyEmployeeArray.get(i).getSocialSecurityNumber(), ssn)){
                    hourlyEmployeeArray.remove(i);
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Removes a salaried employee from the list based on the SSN.
     *
     * @param ssn The social security number of the employee to remove.
     * @throws RuntimeException If an error occurs during the remove operation.
     */
    public void removeFromSalariedEmployee(String ssn){
        try{
            for(int i = 0; i < salariedEmployeeArray.size(); i++){
                if(Objects.equals(salariedEmployeeArray.get(i).getSocialSecurityNumber(), ssn)){
                    salariedEmployeeArray.remove(i);
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a commission employee record based on the SSN.
     *
     * @param ssn The social security number of the employee to retrieve.
     * @return The commission employee record.
     * @throws RuntimeException If an error occurs or the record is not found.
     */
    public ComissionEmployee getRecordFromCommissionEmployee(String ssn){
        try{
            for (ComissionEmployee comissionEmployee : commissionEmployeeArray) {
                if (Objects.equals(comissionEmployee.getSocialSecurityNumber(), ssn)) {
                    return comissionEmployee;
                }
            }
            throw new Exception("Cannot find record.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a base plus commission employee record based on the SSN.
     *
     * @param ssn The social security number of the employee to retrieve.
     * @return The base plus commission employee record.
     * @throws RuntimeException If an error occurs or the record is not found.
     */
    public BasePlusCommissionEmployee getRecordFromBasePlusCommissionEmployee(String ssn){
        try{
            for (BasePlusCommissionEmployee basePlusCommissionEmployee : basePlusCommissionEmployeeArray) {
                if (Objects.equals(basePlusCommissionEmployee.getSocialSecurityNumber(), ssn)) {
                    return basePlusCommissionEmployee;
                }
            }
            throw new Exception("Cannot find record.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves an hourly employee record based on the SSN.
     *
     * @param ssn The social security number of the employee to retrieve.
     * @return The hourly employee record.
     * @throws RuntimeException If an error occurs or the record is not found.
     */
    public HourlyEmployee getRecordFromHourlyEmployee(String ssn){
        try{
            for (HourlyEmployee hourlyEmployee : hourlyEmployeeArray) {
                if (Objects.equals(hourlyEmployee.getSocialSecurityNumber(), ssn)) {
                    return hourlyEmployee;
                }
            }
            throw new Exception("Cannot find record.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a salaried employee record based on the SSN.
     *
     * @param ssn The social security number of the employee to retrieve.
     * @return The salaried employee record.
     * @throws RuntimeException If an error occurs or the record is not found.
     */
    public SalariedEmployee getRecordFromSalariedEmployee(String ssn){
        try{
            for (SalariedEmployee salariedEmployee : salariedEmployeeArray) {
                if (Objects.equals(salariedEmployee.getSocialSecurityNumber(), ssn)) {
                    return salariedEmployee;
                }
            }
            throw new Exception("Cannot find record.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if an employee with the given SSN exists in any of the employee lists.
     *
     * @param ssn The social security number to check.
     * @return {@code true} if an employee with the given SSN exists, {@code false} otherwise.
     */
    public boolean checkSSN(String ssn){
        for (ComissionEmployee comissionEmployee : commissionEmployeeArray) {
            if (Objects.equals(comissionEmployee.getSocialSecurityNumber(), ssn)) {
                return true;
            }
        }
        for (BasePlusCommissionEmployee basePlusCommissionEmployee : basePlusCommissionEmployeeArray) {
            if (Objects.equals(basePlusCommissionEmployee.getSocialSecurityNumber(), ssn)) {
                return true;
            }
        }
        for (HourlyEmployee hourlyEmployee : hourlyEmployeeArray) {
            if (Objects.equals(hourlyEmployee.getSocialSecurityNumber(), ssn)) {
                return true;
            }
        }
        for (SalariedEmployee salariedEmployee : salariedEmployeeArray) {
            if (Objects.equals(salariedEmployee.getSocialSecurityNumber(), ssn)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Removes an employee from any of the employee lists based on the SSN.
     *
     * @param ssn The social security number of the employee to remove.
     * @return {@code true} if an employee was removed, {@code false} otherwise.
     */
    public boolean removeBySSN(String ssn){
        int index = 0;
        for (ComissionEmployee comissionEmployee : commissionEmployeeArray) {
            if (Objects.equals(comissionEmployee.getSocialSecurityNumber(), ssn)) {
                commissionEmployeeArray.remove(index);
                return true;
            }
            index++;
        }
        index = 0;
        for (BasePlusCommissionEmployee basePlusCommissionEmployee : basePlusCommissionEmployeeArray) {
            if (Objects.equals(basePlusCommissionEmployee.getSocialSecurityNumber(), ssn)) {
                basePlusCommissionEmployeeArray.remove(index);
                return true;
            }
            index++;
        }
        index = 0;
        for (HourlyEmployee hourlyEmployee : hourlyEmployeeArray) {
            if (Objects.equals(hourlyEmployee.getSocialSecurityNumber(), ssn)) {
                hourlyEmployeeArray.remove(index);
                return true;
            }
            index++;
        }
        index = 0;
        for (SalariedEmployee salariedEmployee : salariedEmployeeArray) {
            if (Objects.equals(salariedEmployee.getSocialSecurityNumber(), ssn)) {
                salariedEmployeeArray.remove(index);
                return true;
            }
            index++;
        }
        return false;
    }

    /**
     * Prints the pay stub of an employee based on the SSN.
     *
     * @param ssn The social security number of the employee whose pay stub to print.
     * @return {@code true} if the pay stub was printed, {@code false} otherwise.
     */
    public boolean printBySSN(String ssn){
        try{
            int index = 0;
            for (ComissionEmployee comissionEmployee : commissionEmployeeArray) {
                if (Objects.equals(comissionEmployee.getSocialSecurityNumber(), ssn)) {
                    ComissionEmployee employee = commissionEmployeeArray.get(index);
                    employee.writeToFile();
                    return true;
                }
                index++;
            }
            index = 0;
            for (BasePlusCommissionEmployee basePlusCommissionEmployee : basePlusCommissionEmployeeArray) {
                if (Objects.equals(basePlusCommissionEmployee.getSocialSecurityNumber(), ssn)) {
                    BasePlusCommissionEmployee employee = basePlusCommissionEmployeeArray.get(index);
                    employee.writeToFile();
                    return true;
                }
                index++;
            }
            index = 0;
            for (HourlyEmployee hourlyEmployee : hourlyEmployeeArray) {
                if (Objects.equals(hourlyEmployee.getSocialSecurityNumber(), ssn)) {
                    HourlyEmployee employee = hourlyEmployeeArray.get(index);
                    employee.writeToFile();
                    return true;
                }
                index++;
            }
            index = 0;
            for (SalariedEmployee salariedEmployee : salariedEmployeeArray) {
                if (Objects.equals(salariedEmployee.getSocialSecurityNumber(), ssn)) {
                    SalariedEmployee employee = salariedEmployeeArray.get(index);
                    employee.writeToFile();
                    return true;
                }
                index++;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}