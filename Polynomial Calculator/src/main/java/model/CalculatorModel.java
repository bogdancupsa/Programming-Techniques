package model;

import data.model.Polynomial;

import java.util.List;

public class CalculatorModel {
    private Polynomial result;
    private Polynomial remainder;

    public Polynomial getResult() {
        return result;
    }

    public Polynomial getRemainder() {
        return remainder;
    }

    public void setResult(Polynomial result) {
        this.result = result;
    }

    public void setRemainder(Polynomial remainder) {
        this.remainder = remainder;
    }

    public void getAddition(Polynomial p1, Polynomial p2){
        setResult(p1.add(p2));
    }

    public void getSubtraction(Polynomial p1, Polynomial p2){
        setResult(p1.subtract(p2));
    }

    public void getMultiplication(Polynomial p1, Polynomial p2){
        setResult(p1.multiply(p2));
    }

    public void getDivision(Polynomial p1, Polynomial p2){
        List<Polynomial> divisionResult;
        divisionResult = p1.divide(p2);
        setResult(divisionResult.get(0));
        setRemainder(divisionResult.get(1));
    }

    public void getDerivative(Polynomial p){
        setResult(p.differentiate());
    }

    public void getPrimitive(Polynomial p){
        setResult(p.integrate());
    }
}
