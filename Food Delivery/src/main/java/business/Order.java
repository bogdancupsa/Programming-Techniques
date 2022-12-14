package business;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Order implements Serializable {
    private int orderId;
    private int clientId;
    private LocalDateTime date;
    private String strDate;
    private double total;

    public Order(Order key) {
        this.orderId = key.orderId;
        this.clientId = key.clientId;
        this.date = key.date;
        this.strDate = key.strDate;
        this.total = key.total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && clientId == order.clientId && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return orderId * clientId + (int)date.getHour() * date.getDayOfMonth() + date.getMonthValue();
    }

    public Order(int orderId, int clientId, double total) {
        this.orderId = orderId;
        this.clientId = clientId;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        this.date = LocalDateTime.now();
        this.strDate = dtf.format(date);
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getStrDate() {
        return strDate;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", clientId=" + clientId +
                ", date=" + date +
                ", total=" + total +
                '}';
    }
}
