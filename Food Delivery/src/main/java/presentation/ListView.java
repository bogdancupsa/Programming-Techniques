package presentation;

import business.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListView extends JFrame {
    //private JScrollPane scrollPane = new JScrollPane();
    private JPanel[] panels;
    private JButton[] buttons;
    private JLabel[] labels;
    private JButton orderButton = new JButton("Order now!");
    private JButton nextButton = new JButton("Next 10 items");

    public ListView(ArrayList<MenuItem> items, int lvl){
        panels = new JPanel[items.size()];
        buttons = new JButton[items.size()];
        labels = new JLabel[items.size()];

        for(int i = 0; i < items.size(); i++){
            buttons[i] = new JButton("Add to cart");
            labels[i] = new JLabel(items.get(i).toString());
            panels[i] = new JPanel(new FlowLayout());
        }

        int limit;
        if(items.size() - 10 * lvl < 10){
            limit = items.size();
        }else{
            limit = 10 * lvl + 10;
        }
        for(int i = 10 * lvl; i < limit; i++){
            panels[i].setLocation(10, 10 * i + 10);
            panels[i].setSize(500, 100);
            buttons[i].setLocation(10, 50);
            buttons[i].setSize(30, 20);
            panels[i].add(buttons[i]);
            panels[i].add(labels[i]);
            panels[i].setSize(200, 60);
            panels[i].setVisible(true);
            this.add(panels[i]);
        }

        orderButton.setSize(30, 20);
        nextButton.setSize(30, 20);
        this.add(nextButton);
        this.add(orderButton);

        //this.add(scrollPane);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setSize(600, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(12, 0));
        this.setTitle("Products");
    }

    public String getItemName(JButton button){
        int i = 0;
        for(JButton b : buttons){
            if(b.equals(button)){
                return labels[i].getText();
            }
            i++;
        }
        return "";
    }

    public void display(){
        this.setVisible(true);
    }

    public void addActions(ActionListener a1, ActionListener a2, ActionListener a3){
        for(JButton button : buttons){
            button.addActionListener(a1);
        }
        orderButton.addActionListener(a2);
        nextButton.addActionListener(a3);
    }
}
