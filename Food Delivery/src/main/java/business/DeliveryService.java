package business;

import data.BillWriter;
import data.Serializer;
import model.Client;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryService implements IDeliveryServiceProcessing, Subject {

    private ArrayList<MenuItem> items = new ArrayList<>();
    private HashMap<Order, MenuItem> orders = new HashMap<>();
    private presentation.Observer obs;
    private Serializer serializer = new Serializer();

    @Override
    public List<BaseProduct> importProducts() {
        ArrayList<BaseProduct> products = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("products.csv"));
            line = br.readLine();
            while((line = br.readLine()) != null){
                if(line.startsWith("Daily")){
                    int index = line.lastIndexOf(",");
                    List<String> elements = new ArrayList<>();
                    elements.add(line.substring(0, index));
                    elements.add(line.substring(index + 1));
                    CompositeProduct cp = new CompositeProduct(elements.get(0), Double.parseDouble(elements.get(1)));
                    items.add(cp);
                }else {
                    List<String> elements = Stream.of(line.split(",")).map(elem -> new String(elem)).collect(Collectors.toList());
                    BaseProduct prod = new BaseProduct(elements.get(0), Double.parseDouble(elements.get(1)), Integer.parseInt(elements.get(2)), Integer.parseInt(elements.get(3)), Integer.parseInt(elements.get(4)), Integer.parseInt(elements.get(5)), Double.parseDouble(elements.get(6)));
                    products.add(prod);
                    System.out.println(prod);
                }
            }
        } catch (NoSuchElementException | IOException e) {
            e.printStackTrace();
        }
        if(products.isEmpty()){
            return null;
        }else{
            return products.stream().distinct().toList();
        }
    }

    public void addMenu(ArrayList<MenuItem> menu){
        this.items = menu;
    }

    public void setItems(List<BaseProduct> items) {
        this.items.addAll(items);
    }

    public void addItem(MenuItem item){
        this.items.add(item);
    }

    @Override
    public void deleteProduct(String name) {
        BaseProduct baseProduct = new BaseProduct(name, 0.0, 0,0,0,0,0.0);
        Stream<MenuItem> products =  items.stream().filter(i -> i.getClass().getSimpleName().equals("BaseProduct"));
        Stream<BaseProduct> product = products.map(i -> new BaseProduct((BaseProduct) i));
        List<BaseProduct> list = product.toList();
        for(BaseProduct bp : list){
            if(bp.equals(baseProduct)){
                items.remove(bp);
            }
        }
        serializer.writeMenu(items);
    }

    @Override
    public void addProduct(MenuItem item) {
        items.add(item);
        serializer.writeProducts(item);
    }

    @Override
    public void editProduct(BaseProduct bp) {
        deleteProduct(bp.getTitle());
        addProduct(bp);
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public int checkClient(String user, String pass) throws IOException, ClassNotFoundException {
        ArrayList<Client> cList = serializer.getClients();
        for(Client c : cList){
            if(c.getUsername().equals(user) && c.getPassword().equals(pass)){
                return c.getId();
            }
        }
        return -1;
    }

    public void createOrder(String title, double total, int clientId){
        MenuItem item = new MenuItem();
        item.setTitle(title);
        item.setTotal(total);
        Random rand = new Random();
        Order order = new Order(rand.nextInt(500), clientId, total);
        orders.put(order, item);
        System.out.println("Added order with name " + item + " at " + order.hashCode());
        try {
            BillWriter.createBill(order, item);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        notifyObserver(order, item);
        Client c = serializer.findByID(order.getClientId());
        c.setOrders(c.getOrders() + 1);
        serializer.updateClient(c);
        serializer.writeOrder(order, item);
    }

    public void register(presentation.Observer o){
        obs = o;
    }

    public void notifyObserver(Order order, MenuItem item){
        obs.update(order, item);
    }

    public ArrayList<MenuItem> filterItems(ArrayList<String> inputs){
        Stream<MenuItem> stream;
        if(inputs.isEmpty()){
            JOptionPane.showMessageDialog(null, "That was all!");
            return null;
        }
        String category = inputs.get(0);
        String name;
        int calories, proteins, fat, sodium;
        double rating, price;
        switch (category) {
            case "Name" -> {
                name = inputs.get(1);
                stream = items.stream().filter(p -> p.getTitle().contains(name));
            }
            case "Rating" -> {
                rating = Double.parseDouble(inputs.get(1));
                stream = filterStream(category, 0, rating);
            }
            case "Calories" -> {
                calories = Integer.parseInt(inputs.get(1));
                stream = filterStream(category, calories, 0.0);
            }
            case "Proteins" -> {
                proteins = Integer.parseInt(inputs.get(1));
                stream = filterStream(category, proteins, 0.0);
            }
            case "Fat" -> {
                fat = Integer.parseInt(inputs.get(1));
                stream = filterStream(category, fat, 0.0);
            }
            case "Sodium" -> {
                sodium = Integer.parseInt(inputs.get(1));
                stream = filterStream(category, sodium, 0.0);
            }
            default -> {
                price = Double.parseDouble(inputs.get(1));
                stream = filterStream(category, 0, price);
            }
        }
        return convListToArrayList(stream.toList());
    }

    public Stream<BaseProduct> convertMI(){
        Stream<MenuItem> stream;
        Stream<BaseProduct> streamBp;
        stream = items.stream().filter(p -> p.getClass().getSimpleName().equals("BaseProduct"));
        streamBp = stream.map(p -> new BaseProduct((BaseProduct) p));
        return streamBp;
    }

    public Stream<MenuItem> convertBP(Stream<BaseProduct> streamBp){
        Stream<MenuItem> stream;
        stream = streamBp.map(p -> BaseProduct.getSuper(p));
        return stream;
    }

    public Stream<MenuItem> filterStream(String name, int intVal, double doubleVal){
        Stream<BaseProduct> streamBp = convertMI();
        streamBp = switch (name) {
            case "Rating" -> streamBp.filter(p -> p.getRating() > doubleVal);
            case "Calories" -> streamBp.filter(p -> p.getCalories() < intVal);
            case "Proteins" -> streamBp.filter(p -> p.getProteins() < intVal);
            case "Fat" -> streamBp.filter(p -> p.getFats() < intVal);
            case "Sodium" -> streamBp.filter(p -> p.getSodium() < intVal);
            default -> streamBp.filter(p -> p.getPrice() < doubleVal);
        };
        return convertBP(streamBp);
    }

    public ArrayList<MenuItem> convListToArrayList(List<MenuItem> list){
        ArrayList<MenuItem> returned = new ArrayList<>(list);
        return returned;
    }

    public ArrayList<Order> filterOrders(ArrayList<String> inputs){
        String filter = inputs.get(0);
        int startHour, endHour, nbP, nbC, ammo, day, month, nbO;
        switch(filter){
            case "Start Hour - End Hour" :
                startHour = Integer.parseInt(inputs.get(1));
                endHour = Integer.parseInt(inputs.get(2));
                return reportHours(startHour, endHour);
            case "Number Of Orders (Products)" :
                nbP = Integer.parseInt(inputs.get(1));
                return reportProducts(nbP);
            case "Number Of Orders (Client) + Order Amount" :
                nbC = Integer.parseInt(inputs.get(1));
                ammo = Integer.parseInt(inputs.get(2));
                return reportClientAmount(nbC, ammo);
            default :
                day = Integer.parseInt(inputs.get(1));
                month = Integer.parseInt(inputs.get(2));
                nbO = Integer.parseInt(inputs.get(3));
                return reportDayOrders(day, month, nbO);
        }
    }

    public void setOrders(HashMap<Order, MenuItem> orders){
        this.orders = orders;
    }

    public ArrayList<Order> reportHours(int startHour, int endHour){
        Stream<Map.Entry<Order, MenuItem>> stream = orders.entrySet().stream().filter(e -> e.getKey().getDate().getHour() >= startHour);
        Stream<Map.Entry<Order, MenuItem>> stream1 = stream;
        Stream<Map.Entry<Order, MenuItem>> stream2 = stream1.filter(e -> e.getKey().getDate().getHour() <= endHour);
        Stream<Order> orderStream = stream2.map(e -> new Order(e.getKey()));
        ArrayList<Order> returned = new ArrayList<>(orderStream.toList());
        return returned;
    }
    
    public ArrayList<Order> reportProducts(int nbP){
        Stream<Map.Entry<Order, MenuItem>> stream = orders.entrySet().stream().filter(e -> e.getValue().getOrdered() >= nbP);
        Stream<Order> orderStream = stream.map(e -> new Order(e.getKey()));
        ArrayList<Order> returned = new ArrayList<>(orderStream.toList());
        return returned;
    }

    public ArrayList<Order> reportClientAmount(int nbClients, int amount){
        Stream<Client> stream = orders.keySet().stream().map(menuItem -> serializer.findByID(menuItem.getClientId())).distinct();
        Stream<Client> clientStream = stream.filter(c -> c.getOrders() >= nbClients);
        ArrayList<Client> clientList = new ArrayList<>(clientStream.toList());
        ArrayList<Integer> ids = new ArrayList<>(clientList.stream().map(c -> c.getId()).toList());
        Stream<Order> orderStream = orders.keySet().stream().filter(k -> ids.contains(k.getClientId()));
        Stream<Order> orderStream2 = orderStream.filter(o -> o.getTotal() >= amount);
        ArrayList<Order> returned = new ArrayList<>(orderStream2.toList());
        return returned;
    }

    public ArrayList<Order> reportDayOrders(int day, int month, int nbO){
        Stream<Map.Entry<Order, MenuItem>> stream = orders.entrySet().stream().filter(e -> e.getValue().getOrdered() >= nbO);
        Stream<Order> orderStream = stream.map(e -> new Order(e.getKey()));
        Stream<Order> dayStream = orderStream.filter(o -> o.getDate().getDayOfMonth() == day);
        Stream<Order> monthStream = dayStream.filter(o -> o.getDate().getMonthValue() == month);
        ArrayList<Order> returned = new ArrayList<>(monthStream.toList());
        return returned;
    }
}
