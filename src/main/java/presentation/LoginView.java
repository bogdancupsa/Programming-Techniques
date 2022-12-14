package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.*;

public class LoginView extends JPanel {
    private JLabel titleLabel;
    private JLabel userLabel;
    private JPasswordField passField;
    private JTextField userField;
    private JLabel passLabel;
    private JButton logButton;
    private JButton regButton;
    private JLabel regLabel;
    JFrame frame = new JFrame ("Log in!");

    public LoginView() {
        //construct components
        titleLabel = new JLabel ("Log In!");
        userLabel = new JLabel ("Username:");
        passField = new JPasswordField (5);
        userField = new JTextField (5);
        passLabel = new JLabel ("Password:");
        logButton = new JButton ("Log in");
        regButton = new JButton ("Register");
        regLabel = new JLabel ("Don't have an account? Register here!");

        //adjust size and set layout
        setPreferredSize (new Dimension (248, 247));
        setLayout (null);

        //add components
        add (titleLabel);
        add (userLabel);
        add (passField);
        add (userField);
        add (passLabel);
        add (logButton);
        add (regButton);
        add (regLabel);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (100, 10, 100, 25);
        userLabel.setBounds (10, 45, 100, 25);
        passField.setBounds (85, 95, 155, 25);
        userField.setBounds (85, 45, 155, 25);
        passLabel.setBounds (10, 90, 100, 25);
        logButton.setBounds (70, 135, 100, 25);
        regButton.setBounds (70, 215, 100, 25);
        regLabel.setBounds (10, 175, 230, 30);
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

    public void close() {
        frame.setVisible(false);
    }

    public void addActions(ActionListener log, ActionListener reg){
        logButton.addActionListener(log);
        regButton.addActionListener(reg);
    }
}

