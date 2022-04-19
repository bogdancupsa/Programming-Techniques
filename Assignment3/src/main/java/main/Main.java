package main;

import presentation.*;

public class Main {
    public static void main(String[] args){
        StartView start = new StartView();
        ClientView client = new ClientView();
        OrderView order = new OrderView();
        ProductView product = new ProductView();
        ClientBoxView cBox = new ClientBoxView();
        ProductBoxView pBox = new ProductBoxView();
       Controller control = new Controller(start, product, order, client, cBox, pBox);
    }
}
