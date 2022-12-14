package data;

import business.MenuItem;
import business.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BillWriter {

    public static void createBill(Order order, MenuItem item) throws IOException {
        Random rand = new Random();
        int billId = rand.nextInt(1000);
        String name = "bill" + billId + ".txt";
        File file = new File(name);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write(order.getStrDate() + "\n");
        writer.write("Client's id: "+ order.getClientId() + "\n");
        writer.write("Products bought: " + item + "\n");
        writer.write("Total paid: " + order.getTotal());
        writer.flush();
        writer.close();
    }
}
