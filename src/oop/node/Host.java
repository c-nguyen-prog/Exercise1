package oop.node;

import oop.frame.structure.IPv4;
import oop.frame.structure.MAC;

public class Host {

    private String name;
    private MAC mac;
    private IPv4 ip;

    public Host(String name, MAC mac, IPv4 ip) {
        this.name = name;
        this.mac = mac;
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MAC getMac() {
        return mac;
    }

    public void setMac(MAC mac) {
        this.mac = mac;
    }

    public IPv4 getIp() {
        return ip;
    }

    public void setIp(IPv4 ip) {
        this.ip = ip;
    }
}
