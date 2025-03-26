import classes.helpers.PersistentData;
import gui.PayrollManagement;
import gui.StaffManagement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main extends JFrame {
    private JButton staffButton, invoiceButton;
    private StaffManagement staffManagementWindow;
    private PayrollManagement payrollManagement;
    private PersistentData persistentData;


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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}