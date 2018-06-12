package oop.node;

import oop.frame.structure.ARP;
import oop.frame.structure.Ethernet;

public class Switch {

    private Ethernet ethernet;
    private ARP arp;
    private Port sentPort;
    private Port[] ports;
    private SAT sat;

    public Switch(int numberOfPorts) {
        ports = new Port[numberOfPorts];
        for (int i = 0; i < numberOfPorts; i++) {
            ports[i] = new Port(this,i + 1);
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
            if (sat.getEntries()[i] != null ) {
                if (!output.equals("")) {
                    output += " ";
                }
                output += sat.getEntries()[i].getPortId() + " " + sat.getEntries()[i].getMac().toString();
            }
        }
        return output;
    }

    public void forwardETH(Ethernet ethernet) {
        boolean macInSAT = false;
        Port port = null;
        //special case broadcasting
        if (ethernet.getDestMAC().toString().equalsIgnoreCase("FF:FF:FF:FF:FF:FF")) {
            macInSAT = false;
        } else {
            //checks if destination mac address is already in SAT
            for (int i = 0; i < sat.getEntries().length; i++) {
                if (sat.getEntries()[i] != null) {
                    if (ethernet.getDestMAC().toString().equals(sat.getEntries()[i].getMac().toString())) {
                        macInSAT = true;
                        //get the port ID of the mac address
                        for (int j = 0; j < ports.length; j++) {
                            if (ports[j].getPortId() == sat.getEntries()[i].getPortId()) {
                                port = ports[j];
                            }
                        }
                    }
                }
            }
        }
        if (!macInSAT) {
            for (int i = 0; i < ports.length; i++) {
                if (i != sentPort.getPortId() - 1)  {
                    ports[i].sendETHFrame(ethernet);
                }
            }
        } else {
            port.sendETHFrame(ethernet);
        }
    }

    public void forwardARP(ARP arp) {
        boolean macInSAT = false;
        Port port = null;
        //checks if destination mac address is already in SAT
        for (int i = 0; i < sat.getEntries().length; i++) {
            if (sat.getEntries()[i] != null) {
                if (arp.getDestinationMAC().toString().equals(sat.getEntries()[i].getMac().toString())) {
                    macInSAT = true;
                    //get the port ID of the mac address
                    for (int j = 0; j < ports.length; j++) {
                        if (ports[j].getPortId() == sat.getEntries()[i].getPortId()) {
                            port = ports[j];
                        }
                    }
                }
            }
        }
        if (!macInSAT) {
            for (int i = 0; i < ports.length; i++) {
                if (i != sentPort.getPortId() - 1)  {
                    ports[i].sendARPFrame(arp);
                }
            }
        } else {
            port.sendARPFrame(arp);
        }
    }

    public void setEthernet(Ethernet ethernet) {
        this.ethernet = ethernet;
    }

    public void setSentPort(Port port) {
        this.sentPort = port;
    }

    public void setArp(ARP arp) {
        this.arp = arp;
    }

}
