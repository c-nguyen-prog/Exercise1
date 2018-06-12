package oop.node;

import oop.frame.structure.MAC;

/**
 * This class represents an entry in the SAT
 */
public class Entry {

    private MAC mac;
    private int portId;

    public Entry (MAC mac, int portId) {
        this.mac = mac;
        this.portId = portId;
    }

    public MAC getMac() {
        return mac;
    }

    public void setMac(MAC mac) {
        this.mac = mac;
    }

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }
}
