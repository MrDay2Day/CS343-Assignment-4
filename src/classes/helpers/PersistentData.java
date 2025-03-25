package classes.helpers;

import classes.*;

import java.util.ArrayList;
import java.util.Objects;

public class PersistentData {
    private ArrayList<ComissionEmployee> commissionEmployeeArray;
    private ArrayList<BasePlusCommissionEmployee> basePlusCommissionEmployeeArray;
    private ArrayList<HourlyEmployee> hourlyEmployeeArray;
    private ArrayList<SalariedEmployee> salariedEmployeeArray;

    public PersistentData(){
        initialize();
    };

    public void initialize(){
        this.commissionEmployeeArray = new ArrayList<>();
        this.basePlusCommissionEmployeeArray = new ArrayList<>();
        this.hourlyEmployeeArray = new ArrayList<>();
        this.salariedEmployeeArray = new ArrayList<>();
    }

    // Getters
    public ArrayList<ComissionEmployee> getCommissionEmployee(){
        return commissionEmployeeArray;
    }
    public ArrayList<BasePlusCommissionEmployee> getBasePlusCommissionEmployee(){
        return basePlusCommissionEmployeeArray;
    }
    public ArrayList<HourlyEmployee> getHourlyEmployee(){
        return hourlyEmployeeArray;
    }
    public ArrayList<SalariedEmployee> getSalariedEmployee(){
        return salariedEmployeeArray;
    }


    // Adders
    public void addCommissionEmployee(ComissionEmployee comissionEmployee){
        try{
            commissionEmployeeArray.add(comissionEmployee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void addBasePlusCommissionEmployee(BasePlusCommissionEmployee basePlusCommissionEmployee){
        try{
            basePlusCommissionEmployeeArray.add(basePlusCommissionEmployee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void addHourlyEmployee(HourlyEmployee hourlyEmployee){
        try{
            hourlyEmployeeArray.add(hourlyEmployee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void addSalariedEmployee(SalariedEmployee salariedEmployee){
        try{
            salariedEmployeeArray.add(salariedEmployee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Remover
    public void removeFromCommissionEmployee(String ssn){
        try{
            int index = 0;
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
    public void removeFromBasePlusCommissionEmployee(String ssn){
        try{
            int index = 0;
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
    public void removeFromHourlyEmployee(String ssn){
        try{
            int index = 0;
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
    public void removeFromSalariedEmployee(String ssn){
        try{
            int index = 0;
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
