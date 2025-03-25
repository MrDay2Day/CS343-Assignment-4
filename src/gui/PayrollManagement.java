package gui;


import classes.BasePlusCommissionEmployee;
import classes.ComissionEmployee;
import classes.HourlyEmployee;
import classes.SalariedEmployee;
import classes.helpers.NumberFilter;
import classes.helpers.PersistentData;
import enums.StaffType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class PayrollManagement extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JTextField firstNameField, lastNameField, ssnField, grossSalesField, commissionRateField,
            wageField, hoursField, weeklySalaryField, baseSalaryField, paymentField;
    private JLabel grossFieldLabel, paymentLabel, commissionRateLabel, wageLabel, hoursLabel, weeklyLabel, baseSalaryLabel;
    private JButton addButton, clearTableButton, removeButton, printButton, clearButton, saveButton;
    private JComboBox<StaffType> employeeTypeComboBox;
    private int selectedRowIndex = -1;
    private StaffType selectedType;
    private PersistentData persistentData;

    public PayrollManagement() {
        persistentData = new PersistentData();

        setVisible(false);
        setTitle("Employee and Invoice Management");
        Dimension frameSize = new Dimension(950, 400);
        setSize(frameSize);
        setMinimumSize(frameSize);
        setBackground(Color.white);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                tableModel.setRowCount(0);
                clearInputFields();
            }

        });
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);


        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("SSN");
        tableModel.addColumn("Type");
        tableModel.addColumn("Payment Due");
        employeeTable = new JTable(tableModel);
        employeeTable.setBackground(Color.white);
        employeeTable.setBorder(new EmptyBorder(10, 10, 10, 10));
        employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(employeeTable);
        add(tableScrollPane, BorderLayout.WEST);

        // Add selection listener to load row details
        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRowIndex = employeeTable.getSelectedRow();
                System.out.println("Table Index" + selectedRowIndex);
                if (selectedRowIndex != -1) {
                    loadSelectedRowDetails();
                    removeButton.setEnabled(true);
                    saveButton.setEnabled(true);
                }
            }
        });


        JPanel inputPanel = new JPanel(new GridLayout(11, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        inputPanel.add(firstNameLabel);
        firstNameField = new JTextField(30);
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(30);
        inputPanel.add(lastNameField);

        inputPanel.add(new JLabel("SSN:"));
        ssnField = new JTextField();
        inputPanel.add(ssnField);

        inputPanel.add(new JLabel("Employee Type:"));
        employeeTypeComboBox = new JComboBox<>(StaffType.values());
        inputPanel.add(employeeTypeComboBox);

        grossFieldLabel = new JLabel("Gross Sales (Commission):");
        inputPanel.add(grossFieldLabel);
        grossSalesField = new JTextField(15);
        ((AbstractDocument) grossSalesField.getDocument()).setDocumentFilter(new NumberFilter());
        inputPanel.add(grossSalesField);

        commissionRateLabel = new JLabel("Commission Rate (Commission):");
        inputPanel.add(commissionRateLabel);
        commissionRateField = new JTextField(15);
        ((AbstractDocument) commissionRateField.getDocument()).setDocumentFilter(new NumberFilter());
        inputPanel.add(commissionRateField);

        wageLabel = new JLabel("Wage (Hourly):");
        inputPanel.add(wageLabel);
        wageField = new JTextField(15);
        ((AbstractDocument) wageField.getDocument()).setDocumentFilter(new NumberFilter());
        inputPanel.add(wageField);

        hoursLabel = new JLabel("Hours (Hourly):");
        inputPanel.add(hoursLabel);
        hoursField = new JTextField(15);
        ((AbstractDocument) hoursField.getDocument()).setDocumentFilter(new NumberFilter());
        inputPanel.add(hoursField);

        weeklyLabel = new JLabel("Weekly Salary (Salaried):");
        inputPanel.add(weeklyLabel);
        weeklySalaryField = new JTextField(15);
        ((AbstractDocument) weeklySalaryField.getDocument()).setDocumentFilter(new NumberFilter());
        inputPanel.add(weeklySalaryField);

        baseSalaryLabel = new JLabel("Base Salary (Base Plus Commission):");
        inputPanel.add(baseSalaryLabel);
        baseSalaryField = new JTextField(15);
        ((AbstractDocument) baseSalaryField.getDocument()).setDocumentFilter(new NumberFilter());
        inputPanel.add(baseSalaryField);

        paymentLabel = new JLabel("Total Payment Due:");
        inputPanel.add(paymentLabel);
        paymentField = new JTextField(15);
        paymentField.setEditable(false);
        ((AbstractDocument) paymentField.getDocument()).setDocumentFilter(new NumberFilter());
        inputPanel.add(paymentField);

        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.white);



        add(inputPanel, BorderLayout.CENTER);

        // Add ActionListener to ComboBox
        employeeTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFieldVisibility();
            }
        });

        grossFieldLabel.setEnabled(false);
        commissionRateLabel.setEnabled(false);
        wageLabel.setEnabled(false);
        hoursLabel.setEnabled(false);
        weeklyLabel.setEnabled(false);
        baseSalaryLabel.setEnabled(false);

        grossSalesField.setVisible(false);
        commissionRateField.setVisible(false);
        wageField.setVisible(false);
        hoursField.setVisible(false);
        weeklySalaryField.setVisible(false);
        baseSalaryField.setVisible(false);

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Employee");
        removeButton = new JButton("Remove Employee");
        printButton = new JButton("Print Stubs");
        clearButton = new JButton("Clear Fields");
        clearTableButton = new JButton("Clear Table");
        saveButton = new JButton("Save Changes");

        removeButton.setEnabled(false);
        printButton.setEnabled(false);
        saveButton.setEnabled(false);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployeeToTable();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedEmployee();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInputFields();
                selectedRowIndex = -1;
                removeButton.setEnabled(false);
                saveButton.setEnabled(false);
            }
        });

        clearTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
            }
        } );

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChangesToSelectedRow();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(clearTableButton);
        buttonPanel.add(printButton);
        buttonPanel.setBackground(Color.white);
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(buttonPanel, BorderLayout.SOUTH);

        updateFieldVisibility();
    }
    private void loadSelectedRowDetails() {
        if (selectedRowIndex != -1) {
            firstNameField.setText(tableModel.getValueAt(selectedRowIndex, 0).toString());
            lastNameField.setText(tableModel.getValueAt(selectedRowIndex, 1).toString());
            ssnField.setText(tableModel.getValueAt(selectedRowIndex, 2).toString());
            employeeTypeComboBox.setSelectedItem(tableModel.getValueAt(selectedRowIndex, 3).toString());
        }
    }
    private void saveChangesToSelectedRow() {

        if (selectedRowIndex != -1) {
            tableModel.setValueAt(firstNameField.getText(), selectedRowIndex, 0);
            tableModel.setValueAt(lastNameField.getText(), selectedRowIndex, 1);
            tableModel.setValueAt(ssnField.getText(), selectedRowIndex, 2);
            tableModel.setValueAt(employeeTypeComboBox.getSelectedItem(), selectedRowIndex, 3);
            clearInputFields();
            employeeTable.clearSelection();
            selectedRowIndex = -1;
            removeButton.setEnabled(false);
            saveButton.setEnabled(false);
        }
    }
    private void popUp(String message){
        JOptionPane.showMessageDialog(this, message);
    }
    private void removeSelectedEmployee() {
        if (selectedRowIndex != -1) {
            tableModel.removeRow(selectedRowIndex);
            clearInputFields();
            selectedRowIndex = -1;
            removeButton.setEnabled(false);
            saveButton.setEnabled(false);
        }
    }
    private void addEmployeeToTable() {

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String ssn = ssnField.getText();
        String employeeType = String.valueOf(selectedType);

        saveRecord(firstName, lastName, ssn, selectedType);



        tableModel.addRow(new Object[]{firstName, lastName, ssn, employeeType});

        clearInputFields();
    }
    private void clearInputFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        ssnField.setText("");
        grossSalesField.setText("");
        commissionRateField.setText("");
        wageField.setText("");
        hoursField.setText("");
        weeklySalaryField.setText("");
        baseSalaryField.setText("");
        employeeTypeComboBox.setSelectedIndex(0);
        selectedRowIndex = -1;
        employeeTable.clearSelection();

        updateFieldVisibility();
    }
    private void updateFieldVisibility() {
        // Ensure selectedItem is cast safely and checked for null
        Object selectedItem = employeeTypeComboBox.getSelectedItem();
        if (selectedItem instanceof StaffType) {
            selectedType = (StaffType) selectedItem;
        } else {
            selectedType = StaffType.SELECT_STAFF_TYPE; // Default case
        }

        System.out.println("Selected type: " + selectedType);

        // Reset visibility
        grossFieldLabel.setEnabled(false);
        commissionRateLabel.setEnabled(false);
        wageLabel.setEnabled(false);
        hoursLabel.setEnabled(false);
        weeklyLabel.setEnabled(false);
        baseSalaryLabel.setEnabled(false);

        grossSalesField.setVisible(false);
        commissionRateField.setVisible(false);
        wageField.setVisible(false);
        hoursField.setVisible(false);
        weeklySalaryField.setVisible(false);
        baseSalaryField.setVisible(false);

        // Update visibility based on selection
        switch (selectedType) {
            case COMMISSION_EMPLOYEE -> {
                grossFieldLabel.setEnabled(true);
                commissionRateLabel.setEnabled(true);
                grossSalesField.setVisible(true);
                commissionRateField.setVisible(true);
            }
            case HOURLY_EMPLOYEE -> {
                wageLabel.setEnabled(true);
                hoursLabel.setEnabled(true);
                wageField.setVisible(true);
                hoursField.setVisible(true);
            }
            case SALARIED_EMPLOYEE -> {
                weeklyLabel.setEnabled(true);
                weeklySalaryField.setVisible(true);
            }
            case BASE_PLUS_COMMISSION_EMPLOYEE -> {
                grossFieldLabel.setEnabled(true);
                commissionRateLabel.setEnabled(true);
                baseSalaryLabel.setEnabled(true);
                grossSalesField.setVisible(true);
                commissionRateField.setVisible(true);
                baseSalaryField.setVisible(true);
            }
        }

        revalidate();
        repaint();
    }
    private void saveRecord(String firstName, String lastName, String ssn, StaffType staffType){
        try{
            if(firstName.length() <= 0 || lastName.length() <= 0 || ssn.length() <= 0 ){
                popUp("You must provide a valid first name, last name and SSN");
                throw new Exception("Invalid Entry");
            }
            switch (staffType){
                case SALARIED_EMPLOYEE -> {
                    String salariedText = weeklySalaryField.getText();
                    if(salariedText == null || salariedText.length() <= 0) {
                        popUp("A weekly salary is required");
                        throw new Exception("Invalid Entry");
                    }

                    double weekly = Double.parseDouble(salariedText);
                    if(weekly <= 0) {
                        popUp("Weekly Salary must be more than 0.");
                        throw new Exception("Invalid Entry");
                    }
                    SalariedEmployee salariedEmployee = new SalariedEmployee(firstName, lastName, ssn, weekly);
                    persistentData.addSalariedEmployee(salariedEmployee);
                }
                case COMMISSION_EMPLOYEE -> {
                    String grossSaleText = grossSalesField.getText();
                    String commissionText = commissionRateField.getText();
                    if(grossSaleText == null || grossSaleText.length() <= 0
                        || commissionText == null || commissionText.length() <= 0) {
                        popUp("Gross Sales & Commission Rate are required");
                        throw new Exception("Invalid Entry");
                    }
                    double grossSale = Double.parseDouble(grossSaleText);
                    double commissionRate = Double.parseDouble(commissionText);
                    ComissionEmployee comissionEmployee = new ComissionEmployee(firstName, lastName, ssn, grossSale,
                            commissionRate);
                    persistentData.addCommissionEmployee(comissionEmployee);
                }
                case BASE_PLUS_COMMISSION_EMPLOYEE -> {

                    String baseSalaryText = baseSalaryField.getText();
                    String grossSalesText = grossSalesField.getText();
                    String commissionRateText = commissionRateField.getText();

                    if(baseSalaryText == null || baseSalaryText.length() <= 0
                            || grossSalesText == null || grossSalesText.length() <= 0
                            || commissionRateText == null || commissionRateText.length() <= 0) {
                        popUp("Base Salary, Gross Sales & Commission Rate are required");
                        throw new Exception("Invalid Entry");
                    }
                    double baseSalary = Double.parseDouble(baseSalaryText);
                    double grossSales = Double.parseDouble(grossSalesText);
                    double commissionRate = Double.parseDouble(commissionRateText);
                    BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee(firstName,
                            lastName, ssn, grossSales, commissionRate, baseSalary);
                    persistentData.addBasePlusCommissionEmployee(basePlusCommissionEmployee);
                }
                case HOURLY_EMPLOYEE -> {
                    String  wageText = wageField.getText();
                    String  hoursText = hoursField.getText();
                    if(wageText == null || wageText.length() <= 0
                            || hoursText == null || hoursText.length() <= 0) {
                        popUp("Wage & Hours are required");
                        throw new Exception("Invalid Entry");
                    }
                    double wage = Double.parseDouble(wageText);
                    double hours = Double.parseDouble(hoursText);
                    HourlyEmployee hourlyEmployee = new HourlyEmployee(firstName, lastName, ssn, wage, hours);
                    persistentData.addHourlyEmployee(hourlyEmployee);
                }
                default -> {
                    popUp("Please select a employee type.");
                    throw new RuntimeException("Invalid selection");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }




}
