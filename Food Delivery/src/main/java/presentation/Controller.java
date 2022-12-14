package presentation;

import business.*;
import business.MenuItem;
import data.Serializer;
import model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {

    private ArrayList<BaseProduct> meal = new ArrayList<>();
    private ArrayList<String> order = new ArrayList<>();
    private int clientId;
    private int lvl = 0;
    private double total = 0;
    private AdminView adminView;
    private ClientView clientView;
    private ListView listView;
    private LoginView loginView;
    private RegisterView registerView;
    private ProductView productView;
    private ReportView reportView;
    private SearchView searchView;
    private DeliveryService deliveryService = new DeliveryService();
    private EmployeeView employeeView = new EmployeeView();
    private Serializer serializer = new Serializer();
    private ArrayList<MenuItem> found = new ArrayList<>();
    private ReportOrderView reportOrderView;

    public Controller(){
        adminView = new AdminView();
        adminView.addActions(new ImportListener(), new ManageListener(), new ReportListener());
        clientView = new ClientView();
        clientView.addActions(new SearchListener(), new ViewListener());
        loginView = new LoginView();
        loginView.addActions(new LoginListener(), new RegisterListener());
        registerView = new RegisterView();
        registerView.addActions(new RegisteredListener());
        reportView = new ReportView();
        productView = new ProductView();
        searchView = new SearchView();
        searchView.addActions(new FinishSearchListener());
        productView.addActions(new AddListener(), new DeleteListener(), new EditListener(), new MealListener(), new FinishListener());
        loginView.display();
        reportView.addActions(new GenerateReportListener());
    }

    public class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("user: " + loginView.getUser() + " | pass: " + loginView.getPass());
            if(loginView.getUser().equals("admin") && loginView.getPass().equals("admin")){
                adminView.display();
            }else{
                if(loginView.getUser().equals("employee") && loginView.getPass().equals("salary")){
                    employeeView.display();
                }else {
                    try {
                        clientId = deliveryService.checkClient(loginView.getUser(), loginView.getPass());
                        System.out.println(clientId);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    if (clientId != -1) {
                        ArrayList<MenuItem> menu = serializer.getMenu();
                        deliveryService.addMenu(menu);
                        clientView.display();
                    } else {
                        JOptionPane.showMessageDialog(loginView, "User not registered!");
                    }
                }
            }
            //loginView.close();
        }
    }

    public class RegisterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            loginView.close();
            registerView.display();
        }
    }

    public class RegisteredListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            registerView.close();
            Random rand = new Random();
            Client c = new Client(registerView.getUser(), registerView.getPass(), registerView.getPhone(), rand.nextInt(100) );
            serializer.writeClient(c);
            JOptionPane.showMessageDialog(registerView, "Account created! Log in!");
            loginView.display();
        }
    }

    public class ManageListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            productView.display();
        }
    }

    public class ReportListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            reportView.display();
        }
    }

    public class ImportListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            List<BaseProduct> prod = deliveryService.importProducts();
            deliveryService.setItems(prod);
            ArrayList<MenuItem> menu = deliveryService.getItems();
            serializer.writeMenu(menu);
        }
    }

    public class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            deliveryService.deleteProduct(productView.getInput().get(0));
        }
    }

    public class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> input = productView.getInput();
            BaseProduct bp = new BaseProduct(input.get(0), Double.parseDouble(input.get(1)), Integer.parseInt(input.get(2)), Integer.parseInt(input.get(3)), Integer.parseInt(input.get(4)), Integer.parseInt(input.get(5)),Double.parseDouble(input.get(6)));
            deliveryService.addProduct(bp);
        }
    }

    public class EditListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> input = productView.getInput();
            BaseProduct bp = new BaseProduct(input.get(0), Double.parseDouble(input.get(1)), Integer.parseInt(input.get(2)), Integer.parseInt(input.get(3)), Integer.parseInt(input.get(4)), Integer.parseInt(input.get(5)),Double.parseDouble(input.get(6)));
            deliveryService.editProduct(bp);
        }
    }

    public class MealListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> input = productView.getInput();
            BaseProduct bp = (BaseProduct) serializer.findByName(input.get(0) + " ");
            if(bp != null) {
                meal.add(bp);
            }
        }
    }

    public class FinishListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = "Daily Meal: ";
            for(MenuItem menuItem : meal){
                title += menuItem.getTitle() + ",";
            }
            deliveryService.addItem(new CompositeProduct(meal, title));
            deliveryService.addProduct(new CompositeProduct(meal, title));
            meal = new ArrayList<>();
        }
    }

    public class CartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String item = listView.getItemName((JButton) e.getSource());
            int delim = item.lastIndexOf(",");
            String name = "";
            if(item.startsWith("Daily")){
                name = item.substring(0, item.lastIndexOf(","));
            }else {
                name = item.substring(0, item.indexOf(",") - 1);
            }
            MenuItem menuItem = serializer.findByName(name);
            menuItem.setOrdered(menuItem.getOrdered() + 1);
            serializer.updateMenuItem(menuItem);
            double price = Double.parseDouble(item.substring(delim + 1));
            order.add(name);
            total += price;
        }
    }

    public class OrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            deliveryService.register(employeeView);
            String title = "";
            for(String str : order){
                title += str + ", ";
            }
            deliveryService.createOrder(title, total, clientId);
            order = new ArrayList<>();
        }
    }

    public class ViewListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            lvl = 0;
            ArrayList<MenuItem> menu;
            menu = serializer.getMenu();
            deliveryService.addMenu(menu);
            listView = new ListView(menu, lvl);
            listView.addActions(new CartListener(), new OrderListener(), new NextListener());
            listView.display();
        }
    }

    public class NextListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            listView.setVisible(false);
            listView = new ListView(deliveryService.getItems(), ++lvl);
            listView.addActions(new CartListener(), new OrderListener(), new NextListener());
            listView.display();
        }
    }

    public double getTotal(){
        return total;
    }

    public class SearchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            searchView.display();
        }
    }

    public class FinishSearchListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            lvl = 0;
            ArrayList<String> inputs = searchView.getInputs();
            found = deliveryService.filterItems(inputs);
            if(found == null){ return;}
            listView = new ListView(found, lvl);
            listView.addActions(new CartListener(), new OrderListener(), new NextFilterListener());
            listView.display();
        }
    }

    public class NextFilterListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            listView.setVisible(false);
            if(found == null){
                return;
            }
            listView = new ListView(found, ++lvl);
            listView.addActions(new CartListener(), new OrderListener(), new NextFilterListener());
            listView.display();
        }
    }

    public class GenerateReportListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> inputs = reportView.getInputs();
            deliveryService.setOrders(serializer.getOrders());
            ArrayList<Order> orders = deliveryService.filterOrders(inputs);
            reportOrderView = new ReportOrderView(orders);
            reportOrderView.display(inputs.get(0));
        }
    }
}
