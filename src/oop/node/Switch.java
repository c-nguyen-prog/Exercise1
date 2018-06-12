package oop.node;

import oop.frame.structure.ARP;
import oop.frame.structure.Ethernet;

/**
 * This class represents a switch
 */
public class Switch {

    private Ethernet ethernet;
    private ARP arp;
    private Port sentPort;
    private Port[] ports;
    private SAT sat;

    /**
     * Constructor for the switch
     * @param numberOfPorts number of ports in a switch
     */
    public Switch(int numberOfPorts) {
        ports = new Port[numberOfPorts];
        for (int i = 0; i < numberOfPorts; i++) {
            ports[i] = new Port(this,i + 1);
        }
        sat = SAT.createInstance(numberOfPorts);
    }

    /**
     * Getter method for port with a corresponding port ID
     * @param portId port ID of the port
     * @return Port from the port ID
     */
    public Port getPort(int portId) {
        if (portId > 0) {
            return ports[portId - 1];
        }
        return null;
    }

    /**
     * Method used to print out the current SAT entries
     * @return String output of current SAT entries
     */
    public String getSAT() {
        sat = SAT.getInstance();
        String output = "";
        for (int i = 0; i < sat.getEntries().length; i++) {
            if (sat.getEntries()[i] != null ) {
                if (!output.equals("")) {
                    output += " ";
                }
                output += sat.getEntries()[i].getPortId() + " "
                        + sat.getEntries()[i].getMac().toString();
            }
        }
        return output;
    }

    /**
     * Method used to forward ethernet frames to host(s)
     * @param ethernet ethernet frame
     */
    public void forwardETH(Ethernet ethernet) {
        boolean macInSAT = false;
        Port port = null;
        //special case broadcasting
        if (ethernet.getDestMAC().toString().
                equalsIgnoreCase("FF:FF:FF:FF:FF:FF")) {
            macInSAT = false;
        } else {
            //checks if destination mac address is already in SAT
            for (int i = 0; i < sat.getEntries().length; i++) {
                if (sat.getEntries()[i] != null) {
                    if (ethernet.getDestMAC().toString().
                            equals(sat.getEntries()[i].getMac().toString())) {
                        macInSAT = true;
                        //get the port ID of the mac address
                        for (int j = 0; j < ports.length; j++) {
                            if (ports[j].getPortId()
                                    == sat.getEntries()[i].getPortId()) {
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

    /**
     * Method used to forward ARP frames to host(s)
     * @param arp ARP frame
     */
    public void forwardARP(ARP arp) {
        boolean macInSAT = false;
        Port port = null;
        //checks if destination mac address is already in SAT
        for (int i = 0; i < sat.getEntries().length; i++) {
            if (sat.getEntries()[i] != null) {
                if (arp.getDestinationMAC().toString().
                        equals(sat.getEntries()[i].getMac().toString())) {
                    macInSAT = true;
                    //get the port ID of the mac address
                    for (int j = 0; j < ports.length; j++) {
                        if (ports[j].getPortId()
                                == sat.getEntries()[i].getPortId()) {
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

    /**
     * Setter method for ethernet frame saved in switch
     * @param ethernet ethernet frame
     */
    public void setEthernet(Ethernet ethernet) {
        this.ethernet = ethernet;
    }

    /**
     * Setter method for the port that the frame was sent from
     * @param port sent port
     */
    public void setSentPort(Port port) {
        this.sentPort = port;
    }

    /**
     * Setter method for ARP frame saved in switch
     * @param arp arp frame
     */
    public void setArp(ARP arp) {
        this.arp = arp;
    }

}
