package data;

public class Client {
    private final int id;
    private final int arrival;
    private final int service;

    public Client(int idClient, int arrivalTime, int serviceTime){
        id = idClient;                                          //id of the client
        arrival = arrivalTime;                                  //time of the arrival of the client
        service = serviceTime;                                  //time spent to serve the client
    }

    public int getId() {
        return id;
    }

    public int getArrival() {
        return arrival;
    }

    public int getService() {
        return service;
    }

    public String toString(){                                   //prints the contents of the client object in the form
        return "("+getId()+", "+getArrival()+", "+getService()+")";   // (id, arrivalTime, serviceTime).
    }
}
