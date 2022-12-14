package business;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem implements Serializable {

    private ArrayList<BaseProduct> meal;
    private String title;
    private double price;

    @Override
    public double computePrice(){
        double total = 0.0;
        for(BaseProduct baseProduct : meal){
            total += baseProduct.computePrice();
        }
        price = total;
        return total;
    }

    public void addItem(BaseProduct bp){
        meal.add(bp);
    }

    public CompositeProduct(ArrayList<BaseProduct> meal) {
        this.meal = meal;
    }

    @Override
    public String toString() {
       String tostring = "";
      // tostring += title + "(";
//       for(BaseProduct bp : meal){
//           tostring += bp.getTitle() + ", ";
//       }
       tostring+= title + ", " + price;
       return tostring;
    }

    public CompositeProduct(String title, double price) {
        this.title = title;
        this.price = price;
        this.ordered = 0;
    }

    public CompositeProduct(ArrayList<BaseProduct> meal, String title) {
        this.meal = meal;
        this.title = title;
        price = computePrice();
        this.ordered = 0;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
