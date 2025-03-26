package gui;

import classes.Invoice;
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


public class PayrollManagement extends JFrame {

    private JTextField partDescriptionField, quantityField, pricePerItemField, paymentField;
    private JLabel partNumberLabel, partDescriptionLabel, quantityLabel, pricePerItemLabel, paymentLabel;
    private JButton printButton, clearButton;
    private JComboBox<String> itemsComboBox;
    private Invoice invoice;
    public PayrollManagement() {
        setVisible(false);
        setTitle("Invoice Management");
        Dimension frameSize = new Dimension(270, 300);
        setSize(frameSize);
        setMinimumSize(frameSize);
        setBackground(Color.white);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                clearInputFields();
                itemsComboBox.setSelectedIndex(0);
            }

        });
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

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
        ((AbstractDocument) quantityField.getDocument()).setDocumentFilter(new WholeNumbersFilter());
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel(""));

        paymentLabel = new JLabel("Total:");
        inputPanel.add(paymentLabel);
        paymentField = new JTextField(15);
        inputPanel.add(paymentField);

        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.white);

        add(inputPanel, BorderLayout.CENTER);

        partDescriptionField.setEditable(false);
        quantityField.setEditable(false);
        pricePerItemField.setEditable(false);
        paymentField.setEditable(false);

        JPanel buttonPanel = new JPanel();
        printButton = new JButton("Print Invoice");
        clearButton = new JButton("Clear Fields");

        itemsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePartDetails();
            }
        });

        printButton.setEnabled(false);
        clearButton.setEnabled(false);

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(invoice == null){
                    popUp("Please select an item.");
                }else{
                    String value = quantityField.getText();
                    if (value.isEmpty()){
                        popUp("Please quantity greater than 0.");
                    }else{
                        if(invoice != null){
                            invoice.writeToFile();
                            popUp("Successfully printed Invoice for payment\n Thank you!");
                            clearInputFields();
                            itemsComboBox.setSelectedIndex(0);
                        }
                    }
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearInputFields();
                itemsComboBox.setSelectedIndex(0);
            }
        });

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

    private void onQuantityChange() {
        String value = quantityField.getText();
        if (!value.isEmpty()) {
            try {
                int quantity = Integer.parseInt(value);
                if(quantity > 0){
                    String selectedPartNumber = (String) itemsComboBox.getSelectedItem();
                    if (selectedPartNumber != null) {
                        for (PartsListEnum part : PartsListEnum.values()) {
                            if (part.getPart().getPartNumber().equals(selectedPartNumber)) {
                                invoice = new Invoice(selectedPartNumber, part.getPart().getDescription(),quantity,
                                        part.getPart().getPrice() );
                                paymentField.setText(String.format("%.2f", invoice.getPaymentAmount()));
                                break;
                            } else{
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

    private void popUp(String message){
        JOptionPane.showMessageDialog(this, message);
    }

    private void testAll(){

    }
}
