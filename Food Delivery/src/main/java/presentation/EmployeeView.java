package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import business.MenuItem;
import business.Order;

public class EmployeeView extends JPanel implements Observer {

    private JTextArea orderArea;
    private JLabel titleLabel;

    public EmployeeView(){
        //construct components
        orderArea = new JTextArea (10, 1);
        titleLabel = new JLabel ("Incoming orders");

        //adjust size and set layout
        setPreferredSize (new Dimension (810, 270));
        setLayout (null);

        //add components
        add (orderArea);
        add (titleLabel);

        //set component bounds (only needed by Absolute Positioning)
        orderArea.setBounds (10, 30, 800, 230);
        titleLabel.setBounds (15, 0, 100, 25);
    }


    public void display(){
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (this);
        frame.pack();
        frame.setVisible (true);
    }

    public void update(Order order, MenuItem item){
        orderArea.append(order.toString());
        orderArea.append("\n");
        orderArea.append(item.toString());
        orderArea.append("\n");
    }
}
