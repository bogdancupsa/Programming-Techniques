package presentation;

import business.MenuItem;
import business.Order;

public interface Observer {

    public void update(Order order, MenuItem item);
}
