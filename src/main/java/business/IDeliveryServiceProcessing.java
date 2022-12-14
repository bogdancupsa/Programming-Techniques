package business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface IDeliveryServiceProcessing {

    public List<BaseProduct> importProducts();

    public void deleteProduct(String name);

    void addProduct(MenuItem item);

    public void editProduct(BaseProduct bp);

    public void createOrder(String title, double total, int clientId);

    public ArrayList<MenuItem> filterItems(ArrayList<String> inputs);
}
