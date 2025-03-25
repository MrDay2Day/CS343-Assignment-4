import gui.StaffManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JButton staffButton, invoiceButton;
    private StaffManagement staffManagementWindow = new StaffManagement();



    public Main() {
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
        setLocationRelativeTo(null);
        setResizable(false);



        // Button Panel
        JPanel buttonPanel = new JPanel();
        staffButton = new JButton("Manage Employee");
        invoiceButton = new JButton("ManageInvoice");

        staffButton.addActionListener(e -> {
            staffManagementWindow.setVisible(true);
        });



        buttonPanel.add(staffButton);
        buttonPanel.add(invoiceButton);
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