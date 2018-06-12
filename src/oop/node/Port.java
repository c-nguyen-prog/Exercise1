package oop.node;

import java.util.Observable;
import java.util.Observer;

public class Port extends Observable implements Observer {

    private int portId;

    public Port(int portId) {
        this.portId = portId;
    }

    public int getPortId() {
        return portId;
    }

    public void setPortId(int portId) {
        this.portId = portId;
    }

    public void sendFrame(String frame) {
        this.setChanged();
        this.notifyObservers(frame);
    }

    public void update(Observable host, Object frame) {

        if (host instanceof  Host) {
            Host newHost = (Host) host;
            if (frame instanceof String) {
                System.out.println(newHost.getName() + ": " );
            }
        }
    }
}
