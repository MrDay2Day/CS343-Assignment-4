package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StaffManagement extends JFrame {
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private JTextField firstNameField, lastNameField, ssnField, grossSalesField, commissionRateField,
            wageField, hoursField, weeklySalaryField, baseSalaryField, paymentField;
    private JLabel grossFieldLabel, paymentLabel, commissionRateLabel, wageLabel, hoursLabel, weeklyLabel, baseSalaryLabel;
    private JButton addButton, removeButton, printButton, clearButton, saveButton;
    private JComboBox<String> employeeTypeComboBox;
    private int selectedRowIndex = -1;


    public StaffManagement() {
        setVisible(false);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Employee and Invoice Management");
        Dimension frameSize = new Dimension(950, 400);
        setSize(frameSize);
        setMinimumSize(frameSize);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Important!

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                tableModel.setRowCount(0);
            }

        });
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        // Table Setup
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("SSN");
        tableModel.addColumn("Type");
        tableModel.addColumn("Payment Due");
        employeeTable = new JTable(tableModel);
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


        // Input Panel Setup
        JPanel inputPanel = new JPanel(new GridLayout(11, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        inputPanel.add(firstNameLabel);
        firstNameField = new JTextField();
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        inputPanel.add(lastNameField);

        inputPanel.add(new JLabel("SSN:"));
        ssnField = new JTextField();
        inputPanel.add(ssnField);

        inputPanel.add(new JLabel("Employee Type:"));
        employeeTypeComboBox = new JComboBox<>(new String[]{"Select Staff Type", "Commission Employee", "Hourly Employee", "Salaried Employee", "Base Plus Commission Employee"});
        inputPanel.add(employeeTypeComboBox);

        grossFieldLabel = new JLabel("Gross Sales (Commission):");
        inputPanel.add(grossFieldLabel);
        grossSalesField = new JTextField();
        inputPanel.add(grossSalesField);

        commissionRateLabel = new JLabel("Commission Rate (Commission):");
        inputPanel.add(commissionRateLabel);
        commissionRateField = new JTextField();
        inputPanel.add(commissionRateField);

        wageLabel = new JLabel("Wage (Hourly):");
        inputPanel.add(wageLabel);
        wageField = new JTextField();
        inputPanel.add(wageField);

        hoursLabel = new JLabel("Hours (Hourly):");
        inputPanel.add(hoursLabel);
        hoursField = new JTextField();
        inputPanel.add(hoursField);

        weeklyLabel = new JLabel("Weekly Salary (Salaried):");
        inputPanel.add(weeklyLabel);
        weeklySalaryField = new JTextField();
        inputPanel.add(weeklySalaryField);

        baseSalaryLabel = new JLabel("Base Salary (Base Plus Commission):");
        inputPanel.add(baseSalaryLabel);
        baseSalaryField = new JTextField();
        inputPanel.add(baseSalaryField);

        paymentLabel = new JLabel("Total Payment Due:");
        inputPanel.add(paymentLabel);
        paymentField = new JTextField();
        paymentField.setEditable(false);
        inputPanel.add(paymentField);


        add(inputPanel, BorderLayout.CENTER);

        // Add ActionListener to ComboBox
        employeeTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFieldVisibility();
            }
        });

        // Initial field setup
        grossFieldLabel.setEnabled(false);
        commissionRateLabel.setEnabled(false);
        wageLabel.setEnabled(false);
        hoursLabel.setEnabled(false);
        weeklyLabel.setEnabled(false);
        baseSalaryLabel.setEnabled(false);

        // Hide all fields first
        grossSalesField.setVisible(false);
        commissionRateField.setVisible(false);
        wageField.setVisible(false);
        hoursField.setVisible(false);
        weeklySalaryField.setVisible(false);
        baseSalaryField.setVisible(false);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Employee");
        removeButton = new JButton("Remove Employee");
        printButton = new JButton("Print Stubs");
        clearButton = new JButton("Clear Fields");
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
        buttonPanel.add(printButton);
        add(buttonPanel, BorderLayout.SOUTH);


        // Initial visibility update
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

            // Optional: Clear fields and reset selection
            clearInputFields();
            employeeTable.clearSelection();
            selectedRowIndex = -1;
            removeButton.setEnabled(false);
            saveButton.setEnabled(false);
        }
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
        String employeeType = (String) employeeTypeComboBox.getSelectedItem();

        tableModel.addRow(new Object[]{firstName, lastName, ssn, employeeType});

        // Clear input fields
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
        updateFieldVisibility();
        selectedRowIndex = -1;
        employeeTable.clearSelection();
    }

    private void updateFieldVisibility() {
        String selectedType = (String) employeeTypeComboBox.getSelectedItem();

        grossFieldLabel.setEnabled(false);
        commissionRateLabel.setEnabled(false);
        wageLabel.setEnabled(false);
        hoursLabel.setEnabled(false);
        weeklyLabel.setEnabled(false);
        baseSalaryLabel.setEnabled(false);

        // Hide all fields first
        grossSalesField.setVisible(false);
        commissionRateField.setVisible(false);
        wageField.setVisible(false);
        hoursField.setVisible(false);
        weeklySalaryField.setVisible(false);
        baseSalaryField.setVisible(false);

        // Show relevant fields
        if ("Commission Employee".equals(selectedType)) {
            grossFieldLabel.setEnabled(true);
            commissionRateLabel.setEnabled(true);
            grossSalesField.setVisible(true);
            commissionRateField.setVisible(true);
        } else if ("Hourly Employee".equals(selectedType)) {
            wageLabel.setEnabled(true);
            hoursLabel.setEnabled(true);
            wageField.setVisible(true);
            hoursField.setVisible(true);
        } else if ("Salaried Employee".equals(selectedType)) {
            weeklyLabel.setEnabled(true);
            weeklySalaryField.setVisible(true);
        } else if ("Base Plus Commission Employee".equals(selectedType)) {
            grossFieldLabel.setEnabled(true);
            commissionRateLabel.setEnabled(true);
            baseSalaryLabel.setEnabled(true);
            grossSalesField.setVisible(true);
            commissionRateField.setVisible(true);
            baseSalaryField.setVisible(true);
        }

        // Refresh the UI
        revalidate();
        repaint();
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new Main();
//            }
//        });
//    }
}
