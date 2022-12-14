package business;

import java.io.Serializable;

public class MenuItem implements Serializable {

    private String title;
    private double total;
    protected int ordered;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double computePrice() {
        return total;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle(){
        return title;
    }

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }
}
