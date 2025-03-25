import classes.helpers.PersistentData;
import gui.StaffManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JButton staffButton, invoiceButton;
    private StaffManagement staffManagementWindow;
    private PersistentData persistentData;


    public Main() {
        persistentData = new PersistentData();
        staffManagementWindow = new StaffManagement(persistentData);
        staffManagementWindow.setVisible(false);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Employee and Invoice Management");
        Dimension frameSize = new Dimension(500, 200);
        setSize(frameSize);
        setMinimumSize(frameSize);
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

        // Button Panel
        JPanel buttonPanel = new JPanel();
        staffButton = new JButton("Manage Employees");
        invoiceButton = new JButton("Manage Invoices");

        staffButton.addActionListener(e -> {
            staffManagementWindow.setVisible(true);
        });

        buttonPanel.add(staffButton);
        buttonPanel.add(invoiceButton);
        buttonPanel.setBackground(Color.white);
        add(buttonPanel, BorderLayout.CENTER);

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