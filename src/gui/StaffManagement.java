package gui;


import classes.BasePlusCommissionEmployee;
import classes.ComissionEmployee;
import classes.HourlyEmployee;
import classes.SalariedEmployee;
import classes.helpers.CharacterLimiter;
import classes.helpers.NumberFilter;
import classes.helpers.PersistentData;
import enums.StaffType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;


public class StaffManagement extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JTextField firstNameField, lastNameField, ssnField, grossSalesField, commissionRateField,
            wageField, hoursField, weeklySalaryField, baseSalaryField, paymentField;
    private JLabel title, grossFieldLabel, paymentLabel, commissionRateLabel, wageLabel, hoursLabel, weeklyLabel, baseSalaryLabel, commissionCount, baseCommissionCount, hourlyCount, salaryCount;
    private JButton addButton, clearTableButton, removeButton, printButton, clearButton;
    private JComboBox<StaffType> employeeTypeComboBox;
    private int selectedRowIndex = -1;
    private StaffType selectedType;
    private PersistentData persistentData;

    public StaffManagement(PersistentData _persistentData) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        persistentData = _persistentData;

        setVisible(false);
        setTitle("Employee Management");
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
                persistentData.initialize();
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


        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRowIndex = employeeTable.getSelectedRow();
                System.out.println("Table Index" + selectedRowIndex);
                if (selectedRowIndex != -1) {
                    loadSelectedRowDetails();
                    removeButton.setEnabled(true);
                }
            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(11, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        inputPanel.add(firstNameLabel);
        firstNameField = new JTextField(30);
        ((AbstractDocument) firstNameField.getDocument()).setDocumentFilter(new CharacterLimiter(30));
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField(30);
        ((AbstractDocument) lastNameField.getDocument()).setDocumentFilter(new CharacterLimiter(30));
        inputPanel.add(lastNameField);

        inputPanel.add(new JLabel("SSN (15 Characters):"));
        ssnField = new JTextField(15);
        ((AbstractDocument) ssnField.getDocument()).setDocumentFilter(new NumberFilter(15));
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

        paymentLabel = new JLabel("Total Payment Due:");

        title = new JLabel("Employee Count: 0");
        commissionCount = new JLabel("Commission Employee: 0");
        baseCommissionCount = new JLabel("Base Commission Employee: 0");
        hourlyCount = new JLabel("Hourly Employee: 0");
        salaryCount = new JLabel("Salary Employee: 0");
        JPanel titlePanel_1 = new JPanel(new GridLayout(1, 1));
        JPanel titlePanel_2 = new JPanel(new GridLayout(1, 4));
        JPanel titlePanel_3 = new JPanel(new GridLayout(2, 1));
        titlePanel_1.add(title);
        titlePanel_2.add(commissionCount);
        titlePanel_2.add(baseCommissionCount);
        titlePanel_2.add(hourlyCount);
        titlePanel_2.add(salaryCount);
        titlePanel_3.add(titlePanel_1);
        titlePanel_3.add(titlePanel_2);
        titlePanel_3.setBorder(new EmptyBorder(10, 10, 10, 10));
        titlePanel_1.setBackground(Color.white);
        titlePanel_2.setBackground(Color.white);
        titlePanel_3.setBackground(Color.white);
        add(titlePanel_3, BorderLayout.NORTH);

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
        addButton.setMinimumSize(new Dimension(120, 30));
        removeButton = new JButton("Remove Employee");
        removeButton.setMinimumSize(new Dimension(120, 30));
        printButton = new JButton("Print Pay Stub");
        printButton.setMinimumSize(new Dimension(120, 30));
        clearButton = new JButton("Clear Fields");
        clearButton.setMinimumSize(new Dimension(120, 30));
        clearTableButton = new JButton("Clear All");
        clearTableButton.setMinimumSize(new Dimension(120, 30));

        removeButton.setEnabled(false);
        printButton.setEnabled(false);

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRowIndex != -1) {
                    String ssn = tableModel.getValueAt(selectedRowIndex, 2).toString();
                    boolean success = persistentData.printBySSN(ssn);
                    String message = success ? "Successfully Printed PayStub" : "Error Printing PayStub";
                    popUp(message);
                }
            }
        });

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
            }
        });

        clearTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                persistentData.initialize();
                clearInputFields();
            }
        } );


        buttonPanel.add(addButton);
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
            String staffTypeValue = tableModel.getValueAt(selectedRowIndex, 3).toString().trim();
            for (int i = 0; i < employeeTypeComboBox.getItemCount(); i++) {
                StaffType type = employeeTypeComboBox.getItemAt(i);
                System.out.println(staffTypeValue);
                System.out.println(type);

                if (Objects.equals(String.valueOf(type), staffTypeValue)) {
                    employeeTypeComboBox.setSelectedItem(type);
                    switch (type){
                        case HOURLY_EMPLOYEE -> {
                            HourlyEmployee employee = persistentData.getRecordFromHourlyEmployee(
                                    (String) tableModel.getValueAt(selectedRowIndex, 2)
                            );
                            wageField.setText(String.valueOf(employee.getWage()));
                            hoursField.setText(String.valueOf(employee.getHours()));
                        }
                        case SALARIED_EMPLOYEE -> {
                            SalariedEmployee employee = persistentData.getRecordFromSalariedEmployee(
                                    (String) tableModel.getValueAt(selectedRowIndex, 2)
                            );
                            weeklySalaryField.setText(String.valueOf(employee.getWeeklySalary()));
                        }
                        case COMMISSION_EMPLOYEE -> {
                            ComissionEmployee employee = persistentData.getRecordFromCommissionEmployee(
                                    (String) tableModel.getValueAt(selectedRowIndex, 2)
                            );
                            grossSalesField.setText(String.valueOf(employee.getGrossSale()));
                            commissionRateField.setText(String.valueOf(employee.getCommissionRate()));
                        }
                        case BASE_PLUS_COMMISSION_EMPLOYEE -> {
                            BasePlusCommissionEmployee employee = persistentData.getRecordFromBasePlusCommissionEmployee(
                                    (String) tableModel.getValueAt(selectedRowIndex, 2)
                            );
                            grossSalesField.setText(String.valueOf(employee.getGrossSale()));
                            commissionRateField.setText(String.valueOf(employee.getCommissionRate()));
                            baseSalaryField.setText(String.valueOf(employee.getBaseSalary()));
                        }
                        default -> popUp("System Error!\nInvalid Value!");
                    }
                    break;
                }
            }
            paymentField.setText(String.valueOf(tableModel.getValueAt(selectedRowIndex, 4)));
        }
    }
    private void popUp(String message){
        JOptionPane.showMessageDialog(this, message);
    }
    private void removeSelectedEmployee() {
        if (selectedRowIndex != -1) {
            String ssn = tableModel.getValueAt(selectedRowIndex, 2).toString();
            boolean deleted = persistentData.removeBySSN(ssn);

            if(deleted){
                popUp("Successfully deleted SSN: " + ssn);
            }else{
                popUp("Something went wrong for SSN: " + ssn);
            }

            tableModel.removeRow(selectedRowIndex);
            clearInputFields();
            selectedRowIndex = -1;
            removeButton.setEnabled(false);
        }
    }
    private void addEmployeeToTable() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String ssn = ssnField.getText();
        saveRecord(firstName, lastName, ssn, selectedType);

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
        paymentField.setText("");
        employeeTypeComboBox.setSelectedIndex(0);
        selectedRowIndex = -1;
        employeeTable.clearSelection();
        removeButton.setEnabled(false);

        int commissionCountTotal = persistentData.getCommissionEmployee().size();
        int baseCommissionCountTotal = persistentData.getBasePlusCommissionEmployee().size();
        int hourlyCountTotal = persistentData.getHourlyEmployee().size();
        int salaryCountTotal = persistentData.getSalariedEmployee().size();

        int titleTotal = commissionCountTotal + baseCommissionCountTotal + hourlyCountTotal + salaryCountTotal;

        title.setText("Employee Count: " + titleTotal);
        commissionCount.setText("Commission Employee: " + commissionCountTotal);
        baseCommissionCount.setText("Base Commission Employee: " + baseCommissionCountTotal);
        hourlyCount.setText("Hourly Employee: " + hourlyCountTotal);
        salaryCount.setText("Salary Employee: " + salaryCountTotal);

        updateFieldVisibility();
    }
    private void updateFieldVisibility() {
        Object selectedItem = employeeTypeComboBox.getSelectedItem();
        if (selectedItem instanceof StaffType) {
            selectedType = (StaffType) selectedItem;
        } else {
            selectedType = StaffType.SELECT_STAFF_TYPE;
        }

        System.out.println("Selected type: " + selectedType);

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

        addButton.setEnabled(selectedRowIndex <= -1);

        firstNameField.setEditable(selectedRowIndex <= -1);
        lastNameField.setEditable(selectedRowIndex <= -1);
        ssnField.setEditable(selectedRowIndex <= -1);
        employeeTypeComboBox.setEnabled(selectedRowIndex <= -1);
        employeeTypeComboBox.setEnabled(selectedRowIndex <= -1);
        printButton.setEnabled(selectedRowIndex > -1);



        switch (selectedType) {
            case COMMISSION_EMPLOYEE -> {
                grossFieldLabel.setEnabled(true);
                commissionRateLabel.setEnabled(true);

                grossSalesField.setEditable(selectedRowIndex <= -1);
                commissionRateField.setEditable(selectedRowIndex <= -1);

                grossSalesField.setVisible(true);
                commissionRateField.setVisible(true);
            }
            case HOURLY_EMPLOYEE -> {
                wageLabel.setEnabled(true);
                hoursLabel.setEnabled(true);

                wageField.setEditable(selectedRowIndex <= -1);
                hoursField.setEditable(selectedRowIndex <= -1);

                wageField.setVisible(true);
                hoursField.setVisible(true);
            }
            case SALARIED_EMPLOYEE -> {
                weeklyLabel.setEnabled(true);

                weeklySalaryField.setEditable(selectedRowIndex <= -1);

                weeklySalaryField.setVisible(true);
            }
            case BASE_PLUS_COMMISSION_EMPLOYEE -> {
                grossFieldLabel.setEnabled(true);
                commissionRateLabel.setEnabled(true);
                baseSalaryLabel.setEnabled(true);

                grossSalesField.setEditable(selectedRowIndex <= -1);
                commissionRateField.setEditable(selectedRowIndex <= -1);
                baseSalaryField.setEditable(selectedRowIndex <= -1);

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

            boolean exist = persistentData.checkSSN(ssn);
            if(exist){
                popUp("This SSN already exist!");
                return;
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
                    String payment = String.format("%.2f", salariedEmployee.getPaymentAmount());
                    tableModel.addRow(new Object[]{firstName, lastName, ssn, String.valueOf(selectedType), payment});
                    clearInputFields();
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
                    String payment = String.format("%.2f", comissionEmployee.getPaymentAmount());
                    tableModel.addRow(new Object[]{firstName, lastName, ssn, String.valueOf(selectedType), payment});
                    clearInputFields();
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
                    String payment = String.format("%.2f", basePlusCommissionEmployee.getPaymentAmount());
                    tableModel.addRow(new Object[]{firstName, lastName, ssn, String.valueOf(selectedType), payment});
                    clearInputFields();
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
                    String payment = String.format("%.2f", hourlyEmployee.getPaymentAmount());
                    tableModel.addRow(new Object[]{firstName, lastName, ssn, String.valueOf(selectedType), payment});
                    clearInputFields();
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
