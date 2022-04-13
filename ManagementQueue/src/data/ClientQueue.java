package data;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class ClientQueue implements Runnable{
    private final LinkedBlockingQueue<Client> clients;
    private final AtomicInteger waitingTime;
    private final AtomicInteger serviceTime;
    private final AtomicInteger totalWaiting;
    private final int queueID;


    public ClientQueue(int id){
        clients = new LinkedBlockingQueue<>();
        waitingTime = new AtomicInteger(0);
        serviceTime = new AtomicInteger(0);
        totalWaiting = new AtomicInteger(0);
        queueID = id;
    }

    public void addClient(Client client){
        clients.add(client);                                //adds the client to the queue and increases the values
        int toAdd = client.getService();
        waitingTime.getAndAdd(toAdd);         //of both waitingTime for the current clients and the
        totalWaiting.getAndAdd(toAdd);        //waiting time overall.
    }

    public AtomicInteger getTotalWaiting() {
        return totalWaiting;
    }

    public LinkedBlockingQueue<Client> getClients(){
        return clients;
    }

    public AtomicInteger getWaitingTime(){
        return waitingTime;
    }

    public AtomicInteger getServiceTime() {
        return serviceTime;
    }

    public int getQueueID() {
        return queueID;
    }

    @Override
    public void run(){
        while(currentThread().isAlive()) {
            if (clients.size() > 0) {
                try {                                                       //gets the clients from the head of the
                    Client servedClient = clients.peek();                   //queue and interrupts the thread by
                    int timeToServe = servedClient.getService();            //the service time of the current client
                    sleep(1000 * timeToServe);
                    clients.poll();                                         //waiting time is decremented by the service
                    waitingTime.getAndAdd(-servedClient.getService());      //time of the client.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int currentClients(){
        return clients.size();
    }

    @Override
    public String toString(){
        String printed = "";
        printed += "Queue " + queueID + ": ";
        if(clients.size() > 0){
            for(Client c : clients){
                printed += c.toString() + "; ";
            }
        }else{
            printed += "closed";
        }
        return printed;
    }


}
