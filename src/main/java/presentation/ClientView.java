package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ClientView extends JPanel {
    private JLabel titleLabel;
    private JButton viewButton;
    private JButton searchButton;
    private JButton orderButton;
    JFrame frame = new JFrame ("Client Window");

    public ClientView() {
        //construct components
        titleLabel = new JLabel ("Welcome!");
        viewButton = new JButton ("View All Products");
        searchButton = new JButton ("Search Products");
        orderButton = new JButton ("Create An Order!");

        //adjust size and set layout
        setPreferredSize (new Dimension (248, 197));
        setLayout (null);

        //add components
        add (titleLabel);
        add (viewButton);
        add (searchButton);
        add (orderButton);

        //set component bounds (only needed by Absolute Positioning)
        titleLabel.setBounds (20, 10, 100, 25);
        viewButton.setBounds (45, 45, 160, 30);
        searchButton.setBounds (45, 90, 160, 30);
        orderButton.setBounds (45, 135, 160, 30);
    }


    public void display(){
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible (true);
    }

    public void close(){
        frame.setVisible(false);
    }

    public void addActions(ActionListener a1, ActionListener a2){
        searchButton.addActionListener(a1);
        orderButton.addActionListener(a2);
        viewButton.addActionListener(a2);
    }
}
