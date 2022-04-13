package main;

import controller.CalculatorController;
import model.CalculatorModel;
import view.CalculatorView;

public class Main {
    public static void main(String[] args){
        CalculatorController calculatorController = new CalculatorController(new CalculatorView(),new CalculatorModel());
    }
}
