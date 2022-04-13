package data.model;

import java.util.*;

public class Polynomial {
    private List<Monomial> polyList;

    public Polynomial() {
        this.polyList = new ArrayList<>();
    }

    public Polynomial(List<Monomial> polyList) {
        this.polyList = polyList;
    }

    public void setPolyList(List<Monomial> polyList) {
        this.polyList = polyList;
    }

    public Polynomial add(Polynomial srcPolynomial){
        Polynomial result = new Polynomial();
        for (Monomial m: this.polyList) {
            Monomial resultMonomial = new Monomial();
            Double coefficient = m.getCoeff();
            for(Monomial mSrc: srcPolynomial.polyList){
                if(m.getDegree().equals(mSrc.getDegree())){
                    coefficient += mSrc.getCoeff();
                }
            }
            resultMonomial.setCoeff(coefficient);
            resultMonomial.setDegree(m.getDegree());
            result.addMonomial(resultMonomial);
        }
        for(Monomial mSrc : srcPolynomial.polyList){
            boolean used = false;
            for(Monomial m : this.polyList){
                if (mSrc.getDegree().equals(m.getDegree())) {
                    used = true;
                    break;
                }
            }
            if(!used){
                result.addMonomial(mSrc);
            }
        }
        result.normalize();
        result.orderByDegree();
        return result;
    }


    public Polynomial subtract(Polynomial srcPolynomial){
        Polynomial result = new Polynomial();
        for (Monomial m: this.polyList) {
            Monomial resultMonomial = new Monomial();
            Double coefficient = m.getCoeff();
            for(Monomial mSrc: srcPolynomial.polyList){
                if(m.getDegree().equals(mSrc.getDegree())){
                    coefficient -= mSrc.getCoeff();
                }
            }
            resultMonomial.setCoeff(coefficient);
            resultMonomial.setDegree(m.getDegree());
            result.polyList.add(resultMonomial);
        }
        for(Monomial mSrc : srcPolynomial.polyList){
            boolean used = false;
            for(Monomial m : this.polyList){
                if (mSrc.getDegree().equals(m.getDegree())) {
                    used = true;
                    break;
                }
            }
            if(!used){
                result.addMonomial(new Monomial(-mSrc.getCoeff(), mSrc.getDegree()));
            }
        }
        result.normalize();
        result.orderByDegree();
        return result;
    }

    public Polynomial multiply(Polynomial srcPolynomial){
        Polynomial result = new Polynomial();
        for(Monomial m : this.polyList){
            for(Monomial mSrc : srcPolynomial.polyList){
                Monomial resultMonomial = new Monomial();
                resultMonomial.setDegree(m.getDegree() + mSrc.getDegree());
                resultMonomial.setCoeff(m.getCoeff() * mSrc.getCoeff());
                result.polyList.add(resultMonomial);
            }
        }
        result.normalize();
        result.orderByDegree();
        return result;
    }

    public Polynomial differentiate(){
        Polynomial result = new Polynomial();
        for(Monomial mSrc : this.polyList){
            Monomial m = new Monomial();
            if(mSrc.getDegree() != 0){
                m.setCoeff(mSrc.getCoeff() * mSrc.getDegree());
                m.setDegree(mSrc.getDegree() - 1);
                result.polyList.add(m);
            }
        }
        return result;
    }

    public Polynomial integrate(){
        Polynomial result = new Polynomial();
        for(Monomial mSrc : this.polyList){
            Monomial m = new Monomial();
            m.setCoeff(mSrc.getCoeff() / (mSrc.getDegree() + 1));
            m.setDegree(mSrc.getDegree() + 1);
            result.addMonomial(m);
        }
        return result;
    }

    private void orderByDegree(){
        polyList.sort(Comparator.comparing(Monomial::getDegree));
        Collections.reverse(polyList);
    }

    public Integer getMaxDegree(){
        Integer degree = -1;
        for(Monomial m : polyList){
            if(m.getDegree() > degree && !m.getCoeff().equals(0.0)){
                degree = m.getDegree();
            }
        }
        return degree;
    }

    public Monomial getFirstMonomial(){
        Iterator<Monomial> itCheck = polyList.iterator();
        Iterator<Monomial> itReturn = polyList.iterator();
        while(itReturn.hasNext() && itCheck.next().getCoeff().equals(0.0)){
            itReturn.next();
        }
        return itReturn.next();
    }

    public Monomial divideMonomials(Polynomial srcPolynomial){
        Monomial result = new Monomial();
        this.orderByDegree();
        srcPolynomial.orderByDegree();
        Monomial dividend = this.getFirstMonomial();
        Monomial divisor = srcPolynomial.getFirstMonomial();
        result.setCoeff(dividend.getCoeff() / divisor.getCoeff());
        result.setDegree(dividend.getDegree() - divisor.getDegree());
        return result;
    }

    public Polynomial multiplyMonomial(Monomial srcMonomial){
        Polynomial result = new Polynomial();
        for(Monomial m : polyList){
            Monomial monomialResult = new Monomial();
            monomialResult.setDegree(srcMonomial.getDegree() + m.getDegree());
            monomialResult.setCoeff(srcMonomial.getCoeff() * m.getCoeff());
            result.addMonomial(monomialResult);
        }
        return result;
    }

    public List<Polynomial> divide(Polynomial srcPolynomial){
        this.orderByDegree();
        srcPolynomial.orderByDegree();
        Polynomial dividend, divisor, quotient = new Polynomial();
        List<Polynomial> result = new ArrayList<>();
        if(this.getMaxDegree() >= srcPolynomial.getMaxDegree()){
            dividend = this;
            divisor = srcPolynomial;
        }else{
            dividend = srcPolynomial;
            divisor = this;
        }
        while(dividend.getMaxDegree() >= divisor.getMaxDegree()){
            Monomial lastMonomial = dividend.divideMonomials(divisor);
            quotient.addMonomial(lastMonomial);
            Polynomial subtracter = divisor.multiplyMonomial(lastMonomial);
            dividend = dividend.subtract(subtracter);
        }
        result.add(quotient);
        result.add(dividend);
        return result;
    }

    public void addMonomial(Monomial m){
        this.polyList.add(m);
    }

    public void normalize(){
        Polynomial result = new Polynomial();
        for(Monomial mSrc : this.polyList){
            boolean ok = true;
            for(Monomial mRes : result.polyList){
                if(mRes.getDegree().equals(mSrc.getDegree())){
                    ok = false;
                    break;
                }
            }
            if(ok) {
                Monomial newMonomial = new Monomial(mSrc.getCoeff(), mSrc.getDegree());
                for (Monomial m : this.polyList) {
                    if (!mSrc.equals(m)) {
                        if (m.getDegree().equals(mSrc.getDegree())) {
                            newMonomial.setCoeff(m.getCoeff() + newMonomial.getCoeff());
                        }
                    }
                }
                result.addMonomial(newMonomial);
            }
        }
        this.setPolyList(result.polyList);
    }

    public String printPolynomial(){
        boolean first = true;
        String output = "";
        for(Monomial m : this.polyList){
            if(!m.getCoeff().equals(0.0)){
                if (m.getCoeff() > 0){
                    if(first){ first = false;}
                    else{ output += " + "; }
                }
                else{
                    output += " - ";
                    m.setCoeff(-m.getCoeff());
                }
            if(m.getDegree() != 0){
                if(!m.getDegree().equals(1)){
                    output += getStringCoeff(m.getCoeff(),m.getDegree()) + "x^" + m.getDegree();
                }else{
                    output += getStringCoeff(m.getCoeff(),m.getDegree()) +"x";
                }
            }else{
                output += getStringCoeff(m.getCoeff(), m.getDegree());
            }
            }
        }
        return output;
    }

    public String getStringCoeff(Double coefficient, Integer degree){
        coefficient = Math.round(coefficient * 100.0) / 100.0;
        if(coefficient.equals(1.0)){
            if(degree.equals(0)) {
                return "1";
            }else{
                return "";
            }
        }else {
            if (coefficient - coefficient.intValue() == 0) {
                int aux = coefficient.intValue();
                return Integer.toString(aux);
            } else {
                return coefficient.toString();
            }
        }
    }
}
