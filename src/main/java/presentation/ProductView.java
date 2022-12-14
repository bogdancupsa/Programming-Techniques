package presentation;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ProductView extends JPanel {
    private JLabel instrLabel;
    private JLabel titleLabel;
    private JLabel ratingLable;
    private JLabel calLabel;
    private JLabel protLabel;
    private JLabel fatLabel;
    private JLabel sodLabel;
    private JLabel priceLabel;
    private JTextField titleField;
    private JTextField ratField;
    private JTextField calField;
    private JTextField protField;
    private JTextField fatField;
    private JTextField sodField;
    private JTextField priceField;
    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton addToMealButton;
    private JButton finishButton;

    public ProductView() {
        //construct components
        instrLabel = new JLabel ("*For deleting a product, enter only the name");
        titleLabel = new JLabel ("Title:");
        ratingLable = new JLabel ("Rating:");
        calLabel = new JLabel ("Calories:");
        protLabel = new JLabel ("Proteins:");
        fatLabel = new JLabel ("Fat:");
        sodLabel = new JLabel ("Sodium:");
        priceLabel = new JLabel ("Price ($) :");
        titleField = new JTextField (5);
        ratField = new JTextField (5);
        calField = new JTextField (5);
        protField = new JTextField (5);
        fatField = new JTextField (5);
        sodField = new JTextField (5);
        priceField = new JTextField (5);
        addButton = new JButton ("Add Product");
        deleteButton = new JButton ("Delete Product");
        editButton = new JButton ("Modify Product");
        addToMealButton = new JButton ("Add Product To Meal");
        finishButton = new JButton ("Finalize Meal");

        //adjust size and set layout
        setPreferredSize (new Dimension(438, 280));
        setLayout (null);

        //add components
        add (instrLabel);
        add (titleLabel);
        add (ratingLable);
        add (calLabel);
        add (protLabel);
        add (fatLabel);
        add (sodLabel);
        add (priceLabel);
        add (titleField);
        add (ratField);
        add (calField);
        add (protField);
        add (fatField);
        add (sodField);
        add (priceField);
        add (addButton);
        add (deleteButton);
        add (editButton);
        add (addToMealButton);
        add (finishButton);

        //set component bounds (only needed by Absolute Positioning)
        instrLabel.setBounds (10, 10, 260, 25);
        titleLabel.setBounds (10, 50, 100, 25);
        ratingLable.setBounds (10, 80, 100, 25);
        calLabel.setBounds (10, 110, 100, 25);
        protLabel.setBounds (10, 140, 100, 25);
        fatLabel.setBounds (10, 170, 100, 25);
        sodLabel.setBounds (10, 200, 100, 25);
        priceLabel.setBounds (10, 230, 100, 25);
        titleField.setBounds (85, 55, 310, 25);
        ratField.setBounds (85, 85, 100, 25);
        calField.setBounds (85, 115, 100, 25);
        protField.setBounds (85, 145, 100, 25);
        fatField.setBounds (85, 175, 100, 25);
        sodField.setBounds (85, 205, 100, 25);
        priceField.setBounds (85, 235, 100, 25);
        addButton.setBounds (230, 85, 160, 30);
        deleteButton.setBounds (230, 120, 160, 30);
        editButton.setBounds (230, 155, 160, 30);
        addToMealButton.setBounds (230, 190, 160, 30);
        finishButton.setBounds (230, 225, 160, 30);
    }


    public void display(){
        JFrame frame = new JFrame ("Manage Products");
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add (this);
        frame.pack();
        frame.setVisible (true);
        frame.setLocationRelativeTo(null);
    }

    public void addActions(ActionListener a1, ActionListener a2, ActionListener a3, ActionListener a4, ActionListener a5){
        addButton.addActionListener(a1);
        deleteButton.addActionListener(a2);
        editButton.addActionListener(a3);
        addToMealButton.addActionListener(a4);
        finishButton.addActionListener(a5);
    }

    public ArrayList<String> getInput(){
        ArrayList<String> list = new ArrayList<>();
        list.add(titleField.getText());
        list.add(ratField.getText());
        list.add(calField.getText());
        list.add(protField.getText());
        list.add(fatField.getText());
        list.add(sodField.getText());
        list.add(priceField.getText());
        return list;
    }
}
