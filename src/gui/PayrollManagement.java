package gui;

import classes.*;
import classes.helpers.NumberFilter;
import classes.helpers.WholeNumbersFilter;
import enums.PartsListEnum;
import enums.StaffType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Manages the invoice creation and printing functionality.
 * This class provides a graphical user interface for selecting parts, entering quantities,
 * and generating invoices.
 */
public class PayrollManagement extends JFrame {

    /** Text field to display the part description. */
    private JTextField partDescriptionField, quantityField, pricePerItemField, paymentField;
    /** Labels for the input fields. */
    private JLabel partNumberLabel, partDescriptionLabel, quantityLabel, pricePerItemLabel, paymentLabel;
    /** Buttons to print the invoice and clear the input fields. */
    private JButton printButton, clearButton;
    /** Combo box to select the part number. */
    private JComboBox<String> itemsComboBox;
    /** Invoice object to store the invoice details. */
    private Invoice invoice;

    /**
     * Constructs a new PayrollManagement window.
     * Initializes the UI components and sets up event listeners.
     */
    public PayrollManagement() {
        setVisible(false); // Initially hidden
        setTitle("Invoice Management");
        Dimension frameSize = new Dimension(270, 300);
        setSize(frameSize);
        setMinimumSize(frameSize);
        setBackground(Color.white);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Prevents default close behavior
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false); // Hide the window instead of closing
                clearInputFields(); // Clear all input fields
                itemsComboBox.setSelectedIndex(0); // Reset the combo box
            }
        });
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false); // Prevent window resizing

        // Input panel setup
        JPanel inputPanel = new JPanel(new GridLayout(7, 2));

        partNumberLabel = new JLabel("Part Number:");
        inputPanel.add(partNumberLabel);
        itemsComboBox = new JComboBox<>();
        itemsComboBox.addItem("Select a Part");
        for (PartsListEnum part : PartsListEnum.values()) {
            itemsComboBox.addItem(part.getPart().getPartNumber());
        }
        inputPanel.add(itemsComboBox);

        partDescriptionLabel = new JLabel("Part Description:");
        inputPanel.add(partDescriptionLabel);
        partDescriptionField = new JTextField(15);
        inputPanel.add(partDescriptionField);

        pricePerItemLabel = new JLabel("Price Per Unit:");
        inputPanel.add(pricePerItemLabel);
        pricePerItemField = new JTextField(15);
        inputPanel.add(pricePerItemField);

        quantityLabel = new JLabel("Quantity:");
        inputPanel.add(quantityLabel);
        quantityField = new JTextField(15);
        // Apply WholeNumbersFilter to ensure only whole numbers are entered
        ((AbstractDocument) quantityField.getDocument()).setDocumentFilter(new WholeNumbersFilter());
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("")); // Empty labels for spacing
        inputPanel.add(new JLabel(""));

        paymentLabel = new JLabel("Total:");
        inputPanel.add(paymentLabel);
        paymentField = new JTextField(15);
        inputPanel.add(paymentField);

        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding
        inputPanel.setBackground(Color.white);

        add(inputPanel, BorderLayout.CENTER);

        // Disable editing for description, price, and payment fields
        partDescriptionField.setEditable(false);
        quantityField.setEditable(false);
        pricePerItemField.setEditable(false);
        paymentField.setEditable(false);

        // Button panel setup
        JPanel buttonPanel = new JPanel();
        printButton = new JButton("Print Invoice");
        clearButton = new JButton("Clear Fields");

        // Update part details when a part is selected from the combo box
        itemsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePartDetails();
            }
        });

        printButton.setEnabled(false); // Initially disabled
        clearButton.setEnabled(false); // Initially disabled

        // Print invoice button action
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (invoice == null) {
                    popUp("Please select an item.");
                } else {
                    String value = quantityField.getText();
                    if (value.isEmpty()) {
                        popUp("Please quantity greater than 0.");
                    } else {
                        if (invoice != null) {
                            invoice.writeToFile();
                            popUp("Successfully printed Invoice for payment\n Thank you!");
                            clearInputFields();
                            itemsComboBox.setSelectedIndex(0);
                        }
                    }
                }
            }
        });

        // Clear fields button action
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInputFields();
                itemsComboBox.setSelectedIndex(0);
            }
        });

        // Update payment when quantity field changes
        quantityField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                onQuantityChange();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                onQuantityChange();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                onQuantityChange();
            }
        });

        buttonPanel.add(clearButton);
        buttonPanel.add(printButton);
        buttonPanel.setBackground(Color.white);
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Clears all input fields and resets the UI.
     */
    private void clearInputFields() {
        partDescriptionField.setText("");
        quantityField.setText("");
        quantityField.setEditable(false);
        pricePerItemField.setText("");
        paymentField.setText("");
        pricePerItemField.setEditable(false);
        printButton.setEnabled(false);
        clearButton.setEnabled(false);
    }

    /**
     * Updates the payment field when the quantity field changes.
     */
    private void onQuantityChange() {
        String value = quantityField.getText();
        if (!value.isEmpty()) {
            try {
                int quantity = Integer.parseInt(value);
                if (quantity > 0) {
                    String selectedPartNumber = (String) itemsComboBox.getSelectedItem();
                    if (selectedPartNumber != null) {
                        for (PartsListEnum part : PartsListEnum.values()) {
                            if (part.getPart().getPartNumber().equals(selectedPartNumber)) {
                                invoice = new Invoice(selectedPartNumber, part.getPart().getDescription(), quantity,
                                        part.getPart().getPrice());
                                paymentField.setText(String.format("%.2f", invoice.getPaymentAmount()));
                                break;
                            } else {
                                invoice = null;
                                paymentField.setText("");
                            }
                        }
                    }
                } else {
                    invoice = null;
                    paymentField.setText("");
                }

            } catch (NumberFormatException ex) {
                System.err.println("Invalid number format");
            }
        }
    }

    /**
     * Updates the part details when a part is selected from the combo box.
     */
    private void updatePartDetails() {
        String selectedPartNumber = (String) itemsComboBox.getSelectedItem();
        if (selectedPartNumber != null) {
            for (PartsListEnum part : PartsListEnum.values()) {
                if (part.getPart().getPartNumber().equals(selectedPartNumber)) {
                    printButton.setEnabled(true);
                    clearButton.setEnabled(true);

                    partDescriptionField.setText(part.getPart().getDescription());
                    pricePerItemField.setText(String.valueOf(part.getPart().getPrice()));
                    quantityField.setText("0");
                    quantityField.setEditable(true);
                    break;

                } else {
                    clearInputFields();
                }
            }
        }
    }

    /**
     * Displays a pop-up message dialog.
     *
     * @param message The message to display.
     */
    private void popUp(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}