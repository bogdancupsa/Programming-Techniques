package control;

import data.Client;
import data.ClientQueue;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private final List<ClientQueue> queues = new ArrayList<>();
    private final List<Thread> threads = new ArrayList<>();

    public List<ClientQueue> getQueues() {
        return queues;
    }

    public Scheduler(int nbQueues){
        for(int i = 1; i <= nbQueues; i++){                     //generates nbQueues queues and adds them to an ArrayList
            ClientQueue clientQueue = new ClientQueue(i);       //and creates a thread for each queue and adds them to an
            queues.add(clientQueue);                            //an ArrayList of threads, which then are started.
            Thread thread = new Thread(clientQueue);
            threads.add(thread);
            thread.start();
        }
    }

    @Override
    public String toString(){
        String printed = "";
        for(ClientQueue cq : queues){
            printed += cq.toString() + '\n';
        }
        printed += "\n";
        return printed;
    }

    public void addClients(Client client){
        int minimum = Integer.MAX_VALUE;                                        //to add a client, the queue with the minimum waiting time
        int i = 0;                                                              //has to be detected and then the new client is going to be
        for(int index = 0; index < queues.size(); index++){                     //added in the respective queue
            if(queues.get(index).getWaitingTime().intValue() < minimum){
                minimum = queues.get(index).getWaitingTime().intValue();
                i = index;
            }
        }
        queues.get(i).addClient(client);
    }
}
