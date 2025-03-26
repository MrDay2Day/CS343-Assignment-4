import classes.*;
import classes.helpers.PersistentData;
import enums.PartsListEnum;
import gui.PayrollManagement;
import gui.StaffManagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Main class for the Employee and Invoice Management application.
 * This class creates the main window with buttons to manage employees and invoices.
 * It also includes a testing method to create and write test data to files.
 */
public class Main extends JFrame {
    /** Button to open the Staff Management window. */
    private JButton staffButton;
    /** Button to open the Invoice Management window. */
    private JButton invoiceButton;
    /** Instance of the Staff Management window. */
    private StaffManagement staffManagementWindow;
    /** Instance of the Payroll Management window. */
    private PayrollManagement payrollManagement;
    /** Instance of Persistent Data to store and retrieve data. */
    private PersistentData persistentData;

    /**
     * Constructor for the Main class. Initializes the main window, sets up the UI,
     * and creates instances of the Staff and Payroll Management windows.
     */
    public Main() {
        persistentData = new PersistentData();
        staffManagementWindow = new StaffManagement(persistentData);
        staffManagementWindow.setVisible(false);
        payrollManagement = new PayrollManagement();
        payrollManagement.setVisible(false);

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Employee and Invoice Management");
        Dimension frameSize = new Dimension(350, 215);

        setSize(frameSize);
        setMinimumSize(frameSize);
        setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setLocationRelativeTo(null);
        setResizable(false);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(quitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        JPanel textPanel = new JPanel(new GridLayout(1, 3));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        textPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JTextArea staffLabel = new JTextArea("Manage all Employee Payments\n" +
                "Print Pay Stubs for Employees");
        JTextArea invoiceLabel = new JTextArea("Create and Manage Invoice\n" +
                "Print Invoices for Customers");

        staffButton = new JButton("Manage Employees");
        invoiceButton = new JButton("Manage Invoices");

        staffLabel.setEditable(false);
        staffLabel.setOpaque(false);
        staffLabel.setWrapStyleWord(true);
        staffLabel.setLineWrap(true);

        invoiceLabel.setEditable(false);
        invoiceLabel.setOpaque(false);
        invoiceLabel.setWrapStyleWord(true);
        invoiceLabel.setLineWrap(true);

        staffLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        invoiceLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        staffButton.addActionListener(e -> {
            staffManagementWindow.setVisible(true);
        });
        invoiceButton.addActionListener(e -> {
            payrollManagement.setVisible(true);
        });

        textPanel.add(staffLabel);
        textPanel.add(invoiceLabel);

        buttonPanel.add(staffButton);
        buttonPanel.add(invoiceButton);

        textPanel.setBackground(Color.white);
        buttonPanel.setBackground(Color.white);

        add(textPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Main method. Entry point of the application.
     * It calls the {@link #testAll()} method to execute tests and then launches the main window.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Executing Testing method
        testAll();


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new Main();
            }
        });

    }

    /**
     * Private method to test the functionality by creating instances of different employee
     * and invoice classes and writing them to files.
     */
    private static void testAll(){
        // Create Test instances of employees
        ComissionEmployee comissionEmployee = new ComissionEmployee(
                "FirstName1", "LastName1",
                "100000000000001", 230000,
                0.04);
        BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee(
                "FirstName2", "LastName2",
                "100000000000002", 230000,
                0.04, 120000);
        HourlyEmployee hourlyEmployee = new HourlyEmployee(
                "FirstName3", "LastName3",
                "100000000000003", 8200,
                94.56);
        SalariedEmployee salariedEmployee = new SalariedEmployee(
                "FirstName4", "LastName4",
                "100000000000004", 456000);

        // Using Item ENUM to create test instance of invoice
        PartsListEnum part = PartsListEnum.A1;
        Invoice invoice = new Invoice(
                part.getPart().getPartNumber(), part.getPart().getDescription(),
                23, part.getPart().getPrice()
        );

        // Write to file for all instances
        comissionEmployee.writeToFile();
        basePlusCommissionEmployee.writeToFile();
        hourlyEmployee.writeToFile();
        salariedEmployee.writeToFile();
        invoice.writeToFile();
    }
}