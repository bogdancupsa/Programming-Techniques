package controller;

import data.model.Monomial;
import data.model.Polynomial;
import model.CalculatorModel;
import view.CalculatorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class CalculatorController {
    private final CalculatorView theView;
    private final CalculatorModel theModel;

    public CalculatorController(CalculatorView theView, CalculatorModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        theView.addAdditionListener(new AdditionListener());
        theView.addSubtractionListener(new SubtractionListener());
        theView.addMultiplicationListener(new MultiplicationListener());
        theView.addDivisionListener(new DivisionListener());
        theView.addDifferentiationListener(new DifferentiationListener());
        theView.addIntegrationListener(new IntegrationListener());
    }

    public class AdditionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Polynomial firstPolynomial = readPolynomial(true);
                Polynomial secondPolynomial = readPolynomial(false);
                theModel.getAddition(firstPolynomial, secondPolynomial);
                theView.writeResult(theModel.getResult().printPolynomial());
            }catch(NumberFormatException exc){
                System.out.println(exc.getMessage());
            }
        }
    }

    public class SubtractionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Polynomial firstPolynomial = readPolynomial(true);
                Polynomial secondPolynomial = readPolynomial(false);
                theModel.getSubtraction(firstPolynomial, secondPolynomial);
                theView.writeResult(theModel.getResult().printPolynomial());
            }catch(NumberFormatException exc){
                System.out.println(exc.getMessage() + " " + exc.getLocalizedMessage());
            }
        }
    }

    public class MultiplicationListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Polynomial firstPolynomial = readPolynomial(true);
                Polynomial secondPolynomial = readPolynomial(false);
                theModel.getMultiplication(firstPolynomial, secondPolynomial);
                theView.writeResult(theModel.getResult().printPolynomial());
            }catch(NumberFormatException exc){
                System.out.println(exc.getMessage());
            }
        }
    }

    public class DivisionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                Polynomial firstPolynomial = readPolynomial(true);
                Polynomial secondPolynomial = readPolynomial(false);
                theModel.getDivision(firstPolynomial,secondPolynomial);
                theView.printDialogMessage("Quotient: " + theModel.getResult().printPolynomial() + "\n" + "Remainder: " + theModel.getRemainder().printPolynomial());
            }catch(NumberFormatException exc){
                System.out.println(exc.getMessage());
            }
        }
    }

    public class DifferentiationListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Polynomial firstPolynomial = readPolynomial(true);
                theModel.getDerivative(firstPolynomial);
                theView.writeResult(theModel.getResult().printPolynomial());
            }catch(NumberFormatException exc){
                System.out.println(exc.getMessage());
            }
        }
    }

    public class IntegrationListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Polynomial firstPolynomial = readPolynomial(true);
                theModel.getPrimitive(firstPolynomial);
                theView.writeResult(theModel.getResult().printPolynomial());
            }catch(NumberFormatException exception){
                System.out.println(exception.getMessage());
            }
        }
    }

    public Polynomial readPolynomial(boolean first){
        String polyString;
        if(first){
            polyString = theView.getFirstPolynomial();
        }else{
            polyString = theView.getSecondPolynomial();
        }
        String valid = "[^\\dx+^-]";
        if(Pattern.compile(valid).matcher(polyString).find()){
            theView.printDialogMessage("Polynomial input error! Try again.");
            return new Polynomial();
        }else {
            return convertString(polyString);
        }
    }

    public Polynomial convertString(String toBeConverted){
        List<Monomial> monomialList = new ArrayList<>();
        String monomialPattern = "(\\+?-?\\d*x?\\^?\\d?)";
        Pattern pattern = Pattern.compile(monomialPattern);
        Matcher matcher = pattern.matcher(toBeConverted);
        while(matcher.find()){
            Monomial monomial = new Monomial();
            String monomialString = matcher.group(0);
            if(!monomialString.isEmpty()) {
                Pattern degreePattern = Pattern.compile("(\\^\\d*)");
                Matcher degreeMatcher = degreePattern.matcher(monomialString);
                if(degreeMatcher.find() && !degreeMatcher.group().isEmpty()){
                    monomial.setDegree(Integer.parseInt(degreeMatcher.group().substring(1)));
                    String coeffString = monomialString.substring(0, monomialString.length() - degreeMatcher.group().length() - 1);
                    monomial.setCoeff(convertCoeff(coeffString));
                }else{
                    if(monomialString.charAt(monomialString.length() - 1) == 'x') {
                        monomial.setCoeff(convertCoeff(monomialString.substring(0,monomialString.length()-1)));
                        monomial.setDegree(1);
                    }else{
                        monomial.setCoeff(convertCoeff(monomialString));
                        monomial.setDegree(0);
                    }
                }
                monomialList.add(monomial);
            }
        }
        return new Polynomial(monomialList);
    }

    public Double convertCoeff(String s){
        if(s.equals("-")){
            return -1.0;
        }else if(s.equals("+")) {
            return 1.0;
        }else{
            return Double.parseDouble(s);
        }
    }
}
