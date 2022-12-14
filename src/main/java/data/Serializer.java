package data;

import business.BaseProduct;
import business.CompositeProduct;
import business.MenuItem;
import business.Order;
import model.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Serializer {

    public ArrayList<Client> getClients(){
        ObjectInputStream in = null;
        ArrayList<Client> cList = new ArrayList<>();
        try {
            in = new ObjectInputStream(new FileInputStream("clients"));
            cList = (ArrayList<Client>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cList;
    }

    public void writeProducts(MenuItem item){
        try {
            ArrayList<MenuItem> menu;
            try {
                FileInputStream fin = new FileInputStream("menu");
                ObjectInputStream in = new ObjectInputStream(fin);
                menu = (ArrayList<MenuItem>) in.readObject();
            }catch(EOFException eof){
                menu = new ArrayList<>();
            }
            FileOutputStream fout = new FileOutputStream("menu");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            menu.add(item);
            out.writeObject(menu);
            out.flush();
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }

    public void writeClient(Client c){
        try {
            ArrayList<Client> cList;
            try {
                FileInputStream fin = new FileInputStream("clients");
                ObjectInputStream in = new ObjectInputStream(fin);
                cList = (ArrayList<Client>) in.readObject();
            }catch(EOFException eof){
                cList = new ArrayList<>();
            }
            FileOutputStream fout = new FileOutputStream("clients");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            cList.add(c);
            out.writeObject(cList);
            out.flush();
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }

    public void writeMenu(ArrayList<MenuItem> menu){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("menu"));
            out.writeObject(menu);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<MenuItem> getMenu(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("menu"));
             ArrayList<MenuItem> menu =  (ArrayList<MenuItem>) in.readObject();
            menu.forEach(System.out::println);
            return menu;

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void writeOrder(Order order, MenuItem item){
        try {
            HashMap<Order, MenuItem> orders;
            try {
                FileInputStream fin = new FileInputStream("orders");
                ObjectInputStream in = new ObjectInputStream(fin);
                orders = (HashMap<Order, MenuItem>) in.readObject();
            }catch(EOFException eof){
                orders = new HashMap<>();
            }
            FileOutputStream fout = new FileOutputStream("orders");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            orders.put(order, item);
            out.writeObject(orders);
            out.flush();
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }

    public HashMap<Order,MenuItem> getOrders(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("orders"));
            return (HashMap<Order,MenuItem>)in.readObject();

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Client findByID(int id){
        ArrayList<Client> clients = getClients();
        for(Client c : clients){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

    public void updateClient(Client c){
        ArrayList<Client> clients = getClients();
        for(Client client : clients){
            if(c.getUsername().equals(client.getUsername())){
                clients.remove(client);
                break;
            }
        }
        try {
            FileOutputStream fout = new FileOutputStream("clients");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            clients.add(c);
            out.writeObject(clients);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMenuItem(MenuItem item){
        ArrayList<MenuItem> menu = getMenu();
        for(MenuItem menuItem : menu){
            if(menuItem.equals(item)){
                menu.remove(menuItem);
                break;
            }
        }
        try {
            FileOutputStream fout = new FileOutputStream("menu");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            menu.add(item);
            out.writeObject(menu);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MenuItem findByName(String name){
        ArrayList<MenuItem> menu = getMenu();
        for(MenuItem item : menu){
            if(item.getClass().getSimpleName().equals("BaseProduct")) {
                BaseProduct bp = (BaseProduct) item;
                if(item.getTitle().equals(name)){
                    return item;
                }
            }else{
                CompositeProduct cp = (CompositeProduct) item;
                if(item.getTitle().equals(name)){
                    return item;
                }
            }
        }
        return null;
    }
}
