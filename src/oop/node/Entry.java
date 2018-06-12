package oop.node;

import oop.frame.structure.MAC;

/**
 * This class represents an entry in the SAT
 */
public class Entry {

    private MAC mac;
    private int portId;

    /**
     * Constructor to create an entry in SAT
     * @param mac MAC address
     * @param portId port ID
     */
    public Entry (MAC mac, int portId) {
        this.mac = mac;
        this.portId = portId;
    }

    /**
     * Getter method for the MAC address
     * @return MAC address
     */
    public MAC getMac() {
        return mac;
    }

    /**
     * Getter method for the port ID
     * @return port ID
     */
    public int getPortId() {
        return portId;
    }
}
