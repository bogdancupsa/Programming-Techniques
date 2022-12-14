package model;

import java.io.Serializable;

public class Client implements Serializable {
    String username;
    String password;
    String phone;
    int id;
    int orders;

    public Client(String username, String password, String phone, int id) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.id = id;
        this.orders = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", id=" + id +
                ", orders=" + orders +
                '}';
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }
}
