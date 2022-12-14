package presentation;

import business.Order;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class ReportOrderView extends JPanel {
    private JTextArea jcomp1;

    public ReportOrderView(ArrayList<Order> orders) {
        //construct components
        jcomp1 = new JTextArea (5, 5);

        for(Order order : orders){
            jcomp1.append(order.toString());
            jcomp1.append("\n");
        }

        //adjust size and set layout
        setPreferredSize (new Dimension (509, 324));
        BoxLayout layout = new BoxLayout (this, BoxLayout.Y_AXIS);
        setLayout (layout);

        //add components
        add (jcomp1);
    }


    public void display(String cat) {
        JFrame frame = new JFrame (cat);
        frame.setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().add (this);
        frame.pack();
        frame.setVisible (true);
    }
}

