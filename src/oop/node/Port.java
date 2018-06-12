package oop.node;

import oop.frame.structure.ARP;
import oop.frame.structure.Ethernet;

import java.util.Observable;
import java.util.Observer;

public class Port extends Observable implements Observer {

    private Switch aSwitch;
    private int portId;

    public Port(Switch aSwitch, int portId) {
        this.aSwitch = aSwitch;
        this.portId = portId;
    }

    public int getPortId() {
        return portId;
    }

    public void sendETHFrame(Ethernet frame) {
        this.setChanged();
        this.notifyObservers(frame);
    }

    public void sendARPFrame(ARP frame) {
        this.setChanged();
        this.notifyObservers(frame);
    }

    public void update(Observable host, Object frame) {
        SAT sat = SAT.getInstance();

        if (host instanceof  Host) {
            if (frame instanceof Ethernet) {
                for (int i = 0; i < sat.getEntries().length; i++) {
                    if (sat.getEntries()[i] != null) {
                        if (((Ethernet) frame).getSrcMAC().toString().equals(sat.getEntries()[i].getMac().toString())) {
                            sat.removeEntry(sat.getEntries()[i].getPortId());
                        }
                    }
                }
                sat.addEntry(((Ethernet) frame).getSrcMAC(), portId);
                aSwitch.setEthernet((Ethernet) frame);
                aSwitch.setSentPort(this);
            } else if (frame instanceof ARP) {
                for (int i = 0; i < sat.getEntries().length; i++) {
                    if (sat.getEntries()[i] != null) {
                        if (((ARP) frame).getSourceMAC().toString().equals(sat.getEntries()[i].getMac().toString())) {
                            sat.removeEntry(sat.getEntries()[i].getPortId());
                        }
                    }
                }
                sat.addEntry(((ARP) frame).getSourceMAC(), portId);
                aSwitch.setArp((ARP) frame);
                aSwitch.setSentPort(this);
            }
        }
    }

    public Switch getSwitch() {
        return aSwitch;
    }
}
