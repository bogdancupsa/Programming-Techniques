package business;

import java.io.Serializable;
import java.util.Objects;

public class BaseProduct extends MenuItem implements Serializable {

    private String title;
    private double rating;
    private int calories;
    private int proteins;
    private int fats;
    private int sodium;
    private double price;

    @Override
    public double computePrice() {
        return price;
    }

    public BaseProduct(String title, double rating, int calories, int proteins, int fats, int sodium, double price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.sodium = sodium;
        this.price = price;
        this.ordered = 0;
    }

    @Override
    public String toString() {
        return title + " ," + rating + "," +
                calories + "," +
                proteins + "," +
                fats + "," +
                sodium + "," +
                price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseProduct that = (BaseProduct) o;
        return title.equals(that.title);
    }

    public BaseProduct(BaseProduct bp){
        this.title = bp.title;
        this.rating = bp.rating;
        this.calories = bp.calories;
        this.proteins = bp.proteins;
        this.fats = bp.fats;
        this.sodium = bp.sodium;
        this.price = bp.price;
        this.ordered = 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProteins() {
        return proteins;
    }

    public int getFats() {
        return fats;
    }

    public int getSodium() {
        return sodium;
    }

    public double getPrice() {
        return price;
    }

    public static MenuItem getSuper(BaseProduct bp){
        MenuItem item = bp;
        return item;
    }
}
