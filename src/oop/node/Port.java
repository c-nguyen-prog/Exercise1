package oop.node;

import oop.frame.structure.ARP;
import oop.frame.structure.Ethernet;

import java.util.Observable;
import java.util.Observer;

/**
 * This class represents a port
 */
public class Port extends Observable implements Observer {

    private Switch aSwitch;
    private int portId;

    /**
     * Constructor of a port
     * @param aSwitch the switch that the port belongs to
     * @param portId port ID in the switch
     */
    public Port(Switch aSwitch, int portId) {
        this.aSwitch = aSwitch;
        this.portId = portId;
    }

    /**
     * Getter method for the port ID
     * @return port ID
     */
    public int getPortId() {
        return portId;
    }

    /**
     * Method used to send ethernet frame to observers (hosts)
     * @param frame ethernet frame
     */
    public void sendETHFrame(Ethernet frame) {
        this.setChanged();
        this.notifyObservers(frame);
    }

    /**
     * Method used to send ARP frame to observers (hosts)
     * @param frame ARP fram
     */
    public void sendARPFrame(ARP frame) {
        this.setChanged();
        this.notifyObservers(frame);
    }

    @Override
    public void update(Observable host, Object frame) {
        SAT sat = SAT.getInstance();

        if (host instanceof  Host) {
            if (frame instanceof Ethernet) {
                for (int i = 0; i < sat.getEntries().length; i++) {
                    if (sat.getEntries()[i] != null) {
                        if (((Ethernet) frame).getSrcMAC().toString().
                                equals(sat.getEntries()[i].getMac().toString())) {
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
                        if (((ARP) frame).getSourceMAC().toString().
                                equals(sat.getEntries()[i].getMac().toString())) {
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

    /**
     * Getter method for switch
     * @return the switch of the port
     */
    public Switch getSwitch() {
        return aSwitch;
    }
}
