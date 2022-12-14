package business;

public interface Subject {
    public void notifyObserver(Order order, MenuItem item);
    public void register(presentation.Observer o);
}
