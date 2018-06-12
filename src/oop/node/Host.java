package oop.node;

import oop.frame.structure.ARP;
import oop.frame.structure.Ethernet;
import oop.frame.structure.IPv4;
import oop.frame.structure.MAC;

import java.util.Observable;
import java.util.Observer;


/**
 * This class represents a host
 */
public class Host extends Observable implements Observer {

    private String name;
    private MAC mac;
    private IPv4 ip;

    /**
     * Constructor for a host
     * @param name name of the host
     * @param mac MAC address of the host
     * @param ip IPv4 address of the host
     */
    public Host(String name, MAC mac, IPv4 ip) {
        this.name = name;
        this.mac = mac;
        this.ip = ip;
    }

    /**
     * Function used to send ethernet frame
     * @param frame ethernet frame
     */
    public void sendETHFrame(Ethernet frame) {
        this.setChanged();
        this.notifyObservers(frame);
    }

    /**
     * Function used to send ARP frame
     * @param frame arp frame
     */
    public void sendARPFrame(ARP frame) {
        this.setChanged();
        this.notifyObservers(frame);
    }

    @Override
    public void update(Observable port, Object frame) {
        if (port instanceof  Port) {
            Switch aSwitch = ((Port) port).getSwitch();
            if (frame instanceof Ethernet) {
                if (mac.toString().equals(
                        ((Ethernet) frame).getDestMAC().toString())
                        || ((Ethernet) frame).getDestMAC().toString().
                        equalsIgnoreCase("FF:FF:FF:FF:FF:FF")) {
                    System.out.println(name + " received "
                            + ((Ethernet) frame).getPayload().toString()
                            + " from " + ((Ethernet) frame).getSrcMAC().toString());
                }
            } else if (frame instanceof ARP) {
                byte[] replyOpCode = new byte[]{(byte) 0x00, (byte) 0x01};
                if (replyOpCode[1] == ((ARP) frame).getOpcode()[1] ) {
                    if (ip.toString().
                            equals(((ARP) frame).getDestinationIP().toString())) {
                        ARP arp = new ARP(mac, ip, ((ARP) frame).getSourceMAC(),
                                ((ARP) frame).getSourceIP());
                        sendARPFrame(arp);
                        aSwitch.forwardARP(arp);
                    }
                } else {
                    System.out.println(name + ": "
                            + ((ARP) frame).getSourceIP().toIntString()
                            + " is at " + ((ARP) frame).getSourceMAC().toString());
                }
            }
        }
    }

    /**
     *
     * @return
     */
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
