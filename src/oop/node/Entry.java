package oop.node;

import oop.frame.structure.MAC;

public class Entry {

    private MAC mac;
    private int port;

    public Entry (MAC mac, int port) {
        this.mac = mac;
        this.port = port;
    }

    public MAC getMac() {
        return mac;
    }

    public void setMac(MAC mac) {
        this.mac = mac;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
