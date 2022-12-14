package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class ReportView extends JPanel {
    private JLabel instrLabel;
    private JLabel startLabel;
    private JLabel endLabel;
    private JTextField startField;
    private JTextField endField;
    private JLabel nbProduct;
    private JTextField nbPField;
    private JLabel nbClients;
    private JLabel amountLabel;
    private JTextField nbCField;
    private JTextField amountField;
    private JTextField dayField;
    private JTextField monthField;
    private JTextField nbOField;
    private JComboBox jcomp15;
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel nbOrders;
    private JButton genButton;

    public ReportView() {
        //construct preComponents
        String[] jcomp15Items = {"Start Hour - End Hour", "Number Of Orders (Products)", "Number Of Orders (Client) + Order Amount",
                "Specified Day + Number Of Orders"};

        //construct components
        instrLabel = new JLabel ("Select the criteria used for the report and enter the data.");
        startLabel = new JLabel ("Start Hour: ");
        endLabel = new JLabel ("End Hour:");
        startField = new JTextField (5);
        endField = new JTextField (5);
        nbProduct = new JLabel ("Number Of Orders (Products)");
        nbPField = new JTextField (5);
        nbClients = new JLabel ("Number Of Orders (Clients)");
        amountLabel = new JLabel ("Amount Of Order");
        nbCField = new JTextField (5);
        amountField = new JTextField (5);
        dayField = new JTextField (5);
        monthField = new JTextField (5);
        nbOField = new JTextField (5);
        jcomp15 = new JComboBox (jcomp15Items);
        dayLabel = new JLabel ("Day:");
        monthLabel = new JLabel ("Month:");
        nbOrders = new JLabel ("Number of Orders:");
        genButton = new JButton ("Generate Report");

        //adjust size and set layout
        setPreferredSize (new Dimension (337, 411));
        setLayout (null);

        //add components
        add (instrLabel);
        add (startLabel);
        add (endLabel);
        add (startField);
        add (endField);
        add (nbProduct);
        add (nbPField);
        add (nbClients);
        add (amountLabel);
        add (nbCField);
        add (amountField);
        add (dayField);
        add (monthField);
        add (nbOField);
        add (jcomp15);
        add (dayLabel);
        add (monthLabel);
        add (nbOrders);
        add (genButton);

        //set component bounds (only needed by Absolute Positioning)
        instrLabel.setBounds (5, 5, 355, 35);
        startLabel.setBounds (10, 75, 100, 25);
        endLabel.setBounds (170, 75, 100, 25);
        startField.setBounds (10, 105, 100, 25);
        endField.setBounds (170, 105, 100, 25);
        nbProduct.setBounds (10, 140, 180, 25);
        nbPField.setBounds (10, 175, 100, 25);
        nbClients.setBounds (10, 210, 165, 25);
        amountLabel.setBounds (190, 210, 110, 25);
        nbCField.setBounds (10, 245, 100, 25);
        amountField.setBounds (190, 245, 100, 25);
        dayField.setBounds (10, 315, 100, 25);
        monthField.setBounds (115, 315, 100, 25);
        nbOField.setBounds (220, 315, 100, 25);
        jcomp15.setBounds (10, 40, 320, 25);
        dayLabel.setBounds (10, 280, 100, 25);
        monthLabel.setBounds (115, 280, 100, 25);
        nbOrders.setBounds (210, 280, 125, 25);
        genButton.setBounds (70, 355, 190, 40);
    }


    public void display(){
        JFrame frame = new JFrame ("Report Panel");
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add (this);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible (true);
    }

    public ArrayList<String> getInputs(){
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add((String) jcomp15.getSelectedItem());
        switch(inputs.get(0)){
            case "Start Hour - End Hour" :
                inputs.add(startField.getText());
                inputs.add(endField.getText());
                break;
            case "Number Of Orders (Products)" :
                inputs.add(nbPField.getText());
                break;
            case "Number Of Orders (Client) + Order Amount" :
                inputs.add(nbCField.getText());
                inputs.add(amountField.getText());
                break;
            default :
                inputs.add(dayField.getText());
                inputs.add(monthField.getText());
                inputs.add(nbOField.getText());
                break;
        }
        return inputs;
    }

    public void addActions(ActionListener a){
        genButton.addActionListener(a);
    }
}

