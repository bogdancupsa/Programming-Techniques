package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class AdminView extends JPanel {
    private JLabel titleLabel;
    private JButton importButton;
    private JButton manageButton;
    private JButton reportButton;
    private JFrame frame = new JFrame ("Administrator Window");

    public AdminView() {
        //construct components
        titleLabel = new JLabel ("Welcome!");
        importButton = new JButton ("Import Products");
        manageButton = new JButton ("Manage Products");
        reportButton = new JButton ("Generate A Report");

        //adjust size and set layout
        setPreferredSize (new Dimension (248, 189));
        setLayout (null);

        //add components
        add (titleLabel);
        add (importButton);
        add (manageButton);
        add (reportButton);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (20, 10, 100, 25);
        importButton.setBounds (45, 45, 160, 30);
        manageButton.setBounds (45, 90, 160, 30);
        reportButton.setBounds (45, 135, 160, 30);
    }


    public void display(){
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add (this);
        frame.pack();
        frame.setVisible (true);
    }

    public void close(){
        frame.setVisible(false);
    }

    public void addActions(ActionListener a1, ActionListener a2, ActionListener a3){
        importButton.addActionListener(a1);
        manageButton.addActionListener(a2);
        reportButton.addActionListener(a3);
    }
}

