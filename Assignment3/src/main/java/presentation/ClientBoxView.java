package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ClientBoxView extends JPanel {
    private JTextField idField;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel addressLabel;
    private JTextField addressField;
    private JLabel ageLabel;
    private JTextField ageField;
    private JLabel instrLabel;
    private JButton addButton;
    JFrame frame = new JFrame ("Add/Update Clients");

    public ClientBoxView() {
        //construct components
        idField = new JTextField (5);
        idLabel = new JLabel ("Client's ID:");
        nameLabel = new JLabel ("Client's Name:");
        nameField = new JTextField (5);
        addressLabel = new JLabel ("Client's Address:");
        addressField = new JTextField (5);
        ageLabel = new JLabel ("Client's Age:");
        ageField = new JTextField (5);
        instrLabel = new JLabel ("*Only to be inserted when updating a client");
        addButton = new JButton ("Add/Update Client");

        //adjust size and set layout
        setPreferredSize (new Dimension (624, 215));
        setLayout (null);
        setBackground(Color.darkGray);

        //add components
        add (idField);
        add (idLabel);
        add (nameLabel);
        add (nameField);
        add (addressLabel);
        add (addressField);
        add (ageLabel);
        add (ageField);
        add (instrLabel);
        add (addButton);

        //set component bounds (only needed by Absolute Positioning)
        idField.setBounds (140, 5, 125, 30);
        idLabel.setBounds (20, 5, 115, 30);
        idLabel.setForeground(Color.WHITE);
        nameLabel.setBounds (20, 40, 115, 30);
        nameLabel.setForeground(Color.WHITE);
        nameField.setBounds (140, 40, 200, 30);
        addressLabel.setBounds (20, 75, 115, 30);
        addressLabel.setForeground(Color.WHITE);
        addressField.setBounds (140, 75, 200, 30);
        ageLabel.setBounds (20, 110, 115, 30);
        ageLabel.setForeground(Color.WHITE);
        ageField.setBounds (140, 110, 100, 30);
        instrLabel.setBounds (280, 5, 275, 30);
        instrLabel.setForeground(Color.WHITE);
        addButton.setBounds (210, 160, 185, 45);
        addButton.setBackground(Color.gray);
        addButton.setForeground(Color.black);
    }


    public void display(){
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (this);
        frame.pack();
        frame.setVisible (true);
    }

    public void close(){
        frame.setVisible(false);
    }
}
