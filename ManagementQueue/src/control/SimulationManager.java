package control;

import data.Client;
import data.ClientQueue;
import view.SetUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import static java.lang.Thread.sleep;

public class SimulationManager implements Runnable{
    private Scheduler scheduler;
    private Integer simulationTime = 0;
    private Integer nbClients = 0;
    private Integer nbQueues = 0;
    private Integer minArrival = 0;
    private Integer maxArrival = 0;
    private Integer minService = 0;
    private Integer maxService = 0;
    private AtomicInteger time = new AtomicInteger(0);              //current time
    private SetUpView setUpView;                                             //Graphical User Interface
    private ArrayList<Client> newClients = new ArrayList<>();                //an ArrayList for the generated clients
    private BufferedWriter writer;
    private int totalService = 0;

    public SimulationManager(){
        try{
            File file = createFile();
            writer = new BufferedWriter(new FileWriter(file.getName()));
        }catch(IOException e){
            e.printStackTrace();
        }
        setUpView = new SetUpView();
        setUpView.addButtonAction(new ButtonPressed());
        setUpView.viewSetup();
    }

    public Integer getNbClients() {
        return nbClients;
    }

    public void setValues(ArrayList<Integer> inputs){
        this.nbClients = inputs.get(0);
        this.nbQueues = inputs.get(1);
        this.simulationTime = inputs.get(2);
        this.minArrival = inputs.get(3);
        this.maxArrival = inputs.get(4);
        this.minService = inputs.get(5);
        this.maxService = inputs.get(6);
        scheduler = new Scheduler(nbQueues);
    }

    public void generateClients() {
        Random rand = new Random();                                             //generates nbClients client type
        for(int i = 1; i <= nbClients; i++) {                                   //objects with random values (bounded by
            int arrival = rand.nextInt(minArrival, maxArrival + 1);      //minimum and maximum limits from the
            int service = rand.nextInt(minService, maxService + 1);      //inputs) and then add them to an ArrayList.
            newClients.add(new Client(i, arrival, service));                   //the total service time is also calculated here
            totalService += service;
        }
    }

    public int calculateAverageWaiting(){                                   //gets the total waiting time from all the
        int waiting = 0;                                                    //queues, sums them up and divide it by
        for(ClientQueue cq : scheduler.getQueues()){                        //the number of queues
            waiting += cq.getTotalWaiting().intValue();
        }
        return waiting / nbQueues;
    }

    public File createFile() throws IOException {                           //tries to create a file if it does not
        File file = new File("simulation.txt");                         //already exists, otherwise it announces this
        boolean created = file.createNewFile();
        if(created){
            System.out.println("File created");
        }else{
            System.out.println("File already exists");
        }
        return file;
    }

    public void writeFile(String string){
        try{
            writer.write(string);
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(string);
    }

    public void writeSimDetails(int peakHour){
        String string;
        string = "Average waiting time: " + calculateAverageWaiting() + "\n";       //print average waiting time
        writeFile(string);
        string = "Average service time: " + totalService / nbClients + "\n";        //print average service time
        writeFile(string);
        string = "Peak hour: " + peakHour;                                          //print value of peak hour
        writeFile(string);
    }

    public void closeWriter(){
        try {
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void printWaitingClients(){
        String string;
        if(newClients.size() > 0){                              //prints the waiting clients, if there are any
            string = "Waiting clients: ";
            writeFile(string);
        }

        for(int i = 0; i < newClients.size(); i++){                     //if the current time is greater or equal to the
            if(newClients.get(i).getArrival() <= time.intValue()){      //client's arrival time, then they are removed from the
                scheduler.addClients(newClients.get(i));                 //waiting list and added to one of the queues
                newClients.remove(newClients.get(i));
            }
        }

        for(Client c : newClients){
            string = c.toString() + ", ";
            writeFile(string);
        }
        writeFile("\n");
    }

    @Override
    public void run() {
        generateClients();                                          //generates the needed clients for the simulation
        String string;
        int peakHour = 0, maxNbActiveClients = 0, currentClients = 0;
        while(time.intValue() < simulationTime){                    //while the simulation has not reached its maximum time

            string = "\nTime: " + time.toString() + "\n";           //prints the current time of the simulation
            writeFile(string);
            printWaitingClients();

            for(ClientQueue cq : scheduler.getQueues()){            //calculates the peak hour in the following way:
                currentClients += cq.currentClients();              //get the number of clients from each queue
            }                                                       //sums them up
            if(currentClients > maxNbActiveClients){                //compares the sum with the maximum number of active clients
                maxNbActiveClients = currentClients;                //if greater:  -maxNbActiveClients is given the value of the sum
                peakHour = time.intValue();                         //             -peak hour gets the value of the current time
            }
            currentClients = 0;

            writeFile(scheduler.toString());

            try{
                sleep(1000);                            //interrupts the simulation for a second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time.getAndIncrement();                           //increment the current time
        }
        if(newClients.isEmpty() || time.intValue() > simulationTime){
            writeSimDetails(peakHour);              //print average waiting time, avg. service time and peak hour
        }
        closeWriter();
        System.exit(0);
    }

    public class ButtonPressed implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {                    //when the button is pressed, all
            System.out.println("Starting simulation...");               //inputs of the GUI are sent to an ArrayList
            ArrayList<Integer> inputs = setUpView.getInput();           //and then used to set the instance variables of
            setValues(inputs);                                          //the simulation manager
        }
    }

}
