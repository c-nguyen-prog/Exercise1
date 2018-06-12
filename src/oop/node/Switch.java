package oop.node;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class Switch implements Observer {

    private Port[] ports;
    private SAT sat;

    public Switch(int numberOfPorts) {
        ports = new Port[numberOfPorts];
        for (int i = 0; i < numberOfPorts; i++) {
            ports[i] = new Port(i + 1);
        }
        sat = SAT.createInstance(numberOfPorts);
    }

    public Port getPort(int portId) {
        if (portId > 0) {
            return ports[portId - 1];
        }
        return null;
    }

    public String getSAT() {
        sat = SAT.getInstance();
        String output = "";
        for (int i = 0; i < sat.getEntries().length; i++) {
            if (sat.getEntries()[i] != null) {
                output += sat.getEntries()[i].getPort() + " " + sat.getEntries()[i].getMac().toString();
            }
        }
        return output;
    }

    //forward ethernet frame regarding to its table entry


    @Override
    public void update(Observable object, Object frame) {
        System.out.println();
    }
}
