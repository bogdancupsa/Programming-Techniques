package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class SetUpView extends JPanel {
    private final JLabel nbClientsLabel;
    private final JLabel title;
    private final JTextField nbClients;
    private final JLabel queueLabel;
    private final JLabel maxSimulationLabel;
    private final JLabel minArrivalLabel;
    private final JLabel maxServiceLabel;
    private final JLabel minServiceLabel;
    private final JLabel maxArrivalLabel;
    private final JTextField nbQueues;
    private final JTextField simTime;
    private final JTextField minArrival;
    private final JTextField maxArrival;
    private final JTextField minService;
    private final JTextField maxService;
    private  JButton startButton;

    public SetUpView() {
        JPanel panel = new JPanel();
        nbClientsLabel = new JLabel ("Number of clients:");
        title = new JLabel ("      Queue Simulation Setup");
        nbClients = new JTextField (5);
        queueLabel = new JLabel ("Number of queues:");
        maxSimulationLabel = new JLabel ("Maximum simulation time:");
        minArrivalLabel = new JLabel ("Minimum arrival time:");
        maxServiceLabel = new JLabel ("Maximum service time:");
        minServiceLabel = new JLabel ("Minimum service time");
        maxArrivalLabel = new JLabel ("Maximum arrival time:");
        nbQueues = new JTextField (5);
        simTime = new JTextField (5);
        minArrival = new JTextField (5);
        maxArrival = new JTextField (5);
        minService = new JTextField (5);
        maxService = new JTextField (5);
        startButton = new JButton ("Start Simulation");

        nbClientsLabel.setForeground(Color.WHITE);
        maxArrivalLabel.setForeground(Color.WHITE);
        maxServiceLabel.setForeground(Color.WHITE);
        minArrivalLabel.setForeground(Color.WHITE);
        minServiceLabel.setForeground(Color.WHITE);
        maxArrivalLabel.setForeground(Color.WHITE);
        maxSimulationLabel.setForeground(Color.WHITE);
        queueLabel.setForeground(Color.WHITE);
        title.setForeground(Color.WHITE);

        setPreferredSize (new Dimension (390, 519));
        setLayout (null);
        setBackground(Color.DARK_GRAY);

        add(nbClientsLabel);
        add(title);
        add(nbClients);
        add(queueLabel);
        add(maxSimulationLabel);
        add(minArrivalLabel);
        add(maxServiceLabel);
        add(minServiceLabel);
        add(maxArrivalLabel);
        add(nbQueues);
        add(simTime);
        add(minArrival);
        add(maxArrival);
        add(minService);
        add(maxService);
        add(startButton);

        nbClientsLabel.setBounds (30, 40, 140, 40);
        title.setBounds (95, 10, 185, 30);
        nbClients.setBounds (215, 50, 100, 25);
        queueLabel.setBounds (30, 95, 135, 25);
        maxSimulationLabel.setBounds (30, 150, 170, 25);
        minArrivalLabel.setBounds (30, 205, 145, 30);
        maxServiceLabel.setBounds (30, 370, 155, 30);
        minServiceLabel.setBounds (30, 315, 145, 35);
        maxArrivalLabel.setBounds (30, 260, 145, 30);
        nbQueues.setBounds (215, 95, 100, 25);
        simTime.setBounds (215, 150, 100, 25);
        minArrival.setBounds (215, 210, 100, 25);
        maxArrival.setBounds (215, 265, 100, 25);
        minService.setBounds (215, 325, 100, 25);
        maxService.setBounds (215, 375, 100, 25);
        startButton.setBounds (120, 435, 150, 45);
    }

    public void viewSetup() {
        JFrame frame = new JFrame ("Simulation Setup");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public ArrayList<Integer> getInput(){
        ArrayList<Integer> inputs = new ArrayList<>();
        inputs.add(Integer.parseInt(nbClients.getText()));          //nbClients
        inputs.add(Integer.parseInt(nbQueues.getText()));            //nbQueues
        inputs.add(Integer.parseInt(simTime.getText()));            //simTime
        inputs.add(Integer.parseInt(minArrival.getText()));            //minArrival
        inputs.add(Integer.parseInt(maxArrival.getText()));            //maxArrival
        inputs.add(Integer.parseInt(minService.getText()));            //minService
        inputs.add(Integer.parseInt(maxService.getText()));            //maxService
        return inputs;
    }

    public void addButtonAction(ActionListener actionListener){
        startButton.addActionListener(actionListener);
    }

}
