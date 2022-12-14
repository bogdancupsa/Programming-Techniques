package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.*;

public class RegisterView extends JPanel {
    private JLabel titleLabel;
    private JLabel userLabel;
    private JPasswordField passField;
    private JTextField userField;
    private JLabel passLabel;
    private JButton regButton;
    private JLabel phoneLabel;
    private JTextField phoneField;
    JFrame frame = new JFrame ("Register now!");

    public RegisterView() {
        //construct components
        titleLabel = new JLabel ("Register");
        userLabel = new JLabel ("Username:");
        passField = new JPasswordField (5);
        userField = new JTextField (5);
        passLabel = new JLabel ("Password:");
        regButton = new JButton ("Register");
        phoneLabel = new JLabel ("Phone number:");
        phoneField = new JTextField (5);

        //adjust size and set layout
        setPreferredSize (new Dimension (248, 197));
        setLayout (null);

        //add components
        add (titleLabel);
        add (userLabel);
        add (passField);
        add (userField);
        add (passLabel);
        add (regButton);
        add (phoneLabel);
        add (phoneField);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (100, 10, 100, 25);
        userLabel.setBounds (10, 45, 100, 25);
        passField.setBounds (110, 80, 125, 25);
        userField.setBounds (110, 45, 125, 25);
        passLabel.setBounds (10, 80, 100, 25);
        regButton.setBounds (70, 155, 100, 25);
        phoneLabel.setBounds (10, 115, 100, 25);
        phoneField.setBounds (110, 115, 120, 25);
    }


    public void display(){
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (this);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible (true);
    }

    public String getUser(){
        return userField.getText();
    }

    public String getPass(){
        char[] pass =  passField.getPassword();
        String passStr = "";
        for(char c : pass){
            passStr += c;
        }
        return passStr;
    }
    
    public String getPhone(){
        return phoneField.getText();
    }

    public void close(){
        frame.setVisible(false);
    }

    public void addActions(ActionListener actionListener){
        regButton.addActionListener(actionListener);
    }
}

