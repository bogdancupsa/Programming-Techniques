package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorView extends JFrame {

    final private static JButton addButton = new JButton("+");
    final private static  JButton subtractButton = new JButton("-");
    final private static  JButton multiplyButton = new JButton("*");
    final private static JButton divisionButton = new JButton("/");
    final private static  JButton differentiateButton = new JButton("dP(x)/dx");
    final private static  JButton integrateButton = new JButton("/P(x)dx");
    final private static JTextField firstPolyField = new JTextField();
    final private static JTextField secondPolyField = new JTextField();
    final private static JLabel firstPolyLabel = new JLabel("First polynomial:");
    final private static JLabel secondPolyLabel = new JLabel("Second polynomial:");
    final private static JLabel instructionLabel = new JLabel("Write the polynomials in powers");
    final private static JLabel instructionLabelDI =
            new JLabel("Only the first polynomial will be");
    final private static JLabel resultLabel = new JLabel("Result:");
    final private static JLabel resultField = new JLabel();
    final private static JPanel panel = new JPanel();
    final private static JLabel emptyLabel = new JLabel("of x.");
    final private static JLabel emptyLabel2 = new JLabel("used for derivation/integration");

    public void addAddButton(){
        addButton.setLocation(10,810);
        addButton.setSize(30,30);
        addButton.setBackground(Color.darkGray);
        addButton.setForeground(Color.white);
        panel.add(addButton);
    }

    public void addSubtractButton(){
        subtractButton.setLocation(50,810);
        subtractButton.setSize(30,30);
        subtractButton.setBackground(Color.darkGray);
        subtractButton.setForeground(Color.white);
        panel.add(subtractButton);
    }

    public void addMultiplyButton(){
        multiplyButton.setLocation(90,810);
        multiplyButton.setSize(30,30);
        multiplyButton.setBackground(Color.darkGray);
        multiplyButton.setForeground(Color.white);
        panel.add(multiplyButton);
    }

    public void addDivideButton(){
        divisionButton.setLocation(90,810);
        divisionButton.setSize(30,30);
        divisionButton.setBackground(Color.darkGray);
        divisionButton.setForeground(Color.white);
        panel.add(divisionButton);
    }

    public void addDifferentiateButton(){
        resultLabel.setLocation(10,850);
        resultLabel.setSize(30,30);
        differentiateButton.setBackground(Color.darkGray);
        differentiateButton.setForeground(Color.white);
        panel.add(differentiateButton);
    }

    public void addIntegrateButton(){
        integrateButton.setLocation(50,850);
        integrateButton.setSize(30,30);
        integrateButton.setBackground(Color.darkGray);
        integrateButton.setForeground(Color.white);
        panel.add(integrateButton);
    }

    public void addInstructions(){
        instructionLabel.setLocation(10,10);
        instructionLabel.setSize(300,150);
        instructionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        instructionLabel.setBackground(Color.white);
        instructionLabelDI.setLocation(10,170);
        instructionLabelDI.setSize(300,150);
        instructionLabelDI.setHorizontalAlignment(SwingConstants.LEFT);
        instructionLabelDI.setBackground(Color.darkGray);
        panel.add(instructionLabel);
        panel.add(emptyLabel);
        panel.add(instructionLabelDI);
        panel.add(emptyLabel2);
    }

    public void addFirstPolynomial(){
        firstPolyLabel.setLocation(10,330);
        firstPolyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstPolyLabel.setSize(150,150);
        firstPolyLabel.setBackground(Color.darkGray);
        firstPolyField.setLocation(170,330);
        firstPolyField.setSize(150,50);
        panel.add(firstPolyLabel);
        panel.add(firstPolyField);
    }

    public void addSecondPolynomial(){
        secondPolyLabel.setLocation(10,490);
        secondPolyLabel.setSize(150,150);
        secondPolyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        secondPolyLabel.setBackground(Color.darkGray);
        secondPolyField.setLocation(170,490);
        secondPolyField.setSize(150,50);
        panel.add(secondPolyLabel);
        panel.add(secondPolyField);
    }

    public void addResult(){
        resultLabel.setLocation(10,650);
        resultLabel.setSize(150,150);
        resultLabel.setBackground(Color.darkGray);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultField.setLocation(170,650);
        resultField.setSize(150,50);
        resultField.setBackground(Color.darkGray);
        resultField.setForeground(Color.darkGray);
        panel.add(resultLabel);
        panel.add(resultField);
    }

    public CalculatorView() {
        setTitle("Polynomial Calculator");
        setSize(400, 500);
        setBackground(Color.GRAY);
        setDefaultCloseOperation(EXIT_ON_CLOSE );
        setLocationRelativeTo(null);
        panel.setBounds(0,0,400,500);
        panel.setBackground(Color.gray);
        panel.setLayout(new GridLayout(8,2, 10, 20));

        addInstructions();
        addFirstPolynomial();
        addSecondPolynomial();
        addResult();
        addAddButton();
        addSubtractButton();
        addMultiplyButton();
        addDivideButton();
        addDifferentiateButton();
        addIntegrateButton();
        add(panel);

        setVisible(true);

    }

    public void writeResult(String result){
        resultField.setText(result);
    }

    public String getFirstPolynomial(){
        return firstPolyField.getText();
    }

    public String getSecondPolynomial(){
        return secondPolyField.getText();
    }

    public void printDialogMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public void addAdditionListener(ActionListener listenForCalcButton){
        addButton.addActionListener(listenForCalcButton);
    }

    public void addSubtractionListener(ActionListener listenForCalcButton){
        subtractButton.addActionListener(listenForCalcButton);
    }

    public void addMultiplicationListener(ActionListener listenForCalcButton){
        multiplyButton.addActionListener(listenForCalcButton);
    }

    public void addDifferentiationListener(ActionListener listenForCalcButton){
        differentiateButton.addActionListener(listenForCalcButton);
    }

    public void addIntegrationListener(ActionListener listenForCalcButton){
        integrateButton.addActionListener(listenForCalcButton);
    }

    public void addDivisionListener(ActionListener listenForCalcButton){
        divisionButton.addActionListener(listenForCalcButton);
    }
}
