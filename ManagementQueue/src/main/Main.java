package main;

import control.SimulationManager;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SimulationManager sim = new SimulationManager();
        while(sim.getNbClients() == 0){                     //waits for the user to start the simulation by
            Thread.sleep(1000);                       //pressing the button after they inserted the values
        }
        Thread thread = new Thread(sim);
        thread.start();                                     //starts the simulation
    }
}
