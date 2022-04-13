package data.model;

public class Monomial {
    private Double coeff;
    private Integer degree;

    public Double getCoeff() {
        return coeff;
    }

    public void setCoeff(Double coeff) {
        this.coeff = coeff;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    public Monomial() {
        this.coeff = 1.0;
        this.degree = 0;
    }

    public Monomial(Double coeff, Integer degree) {
        this.coeff = coeff;
        this.degree = degree;
    }
}
