package oop.node;

import oop.frame.structure.MAC;

import java.util.Observable;
import java.util.Observer;

/**
 *
 */
public class SAT {

    private Entry[] entries = null;
    private static SAT instance = null;

    private SAT(int numberOfPorts) {
        entries = new Entry[numberOfPorts];
    }

    /**
     *
     * @param numberOfPorts
     * @return
     */
    public static SAT createInstance(int numberOfPorts) {
        if (instance == null) {
            instance = new SAT(numberOfPorts);
        }
        return instance;
    }

    public static SAT getInstance() {
        return instance;
    }

    public void addEntry(MAC mac, int portId) {
        Entry entry = new Entry(mac, portId);
        entries[portId - 1] = entry;
    }

    public void removeEntry(int portId) {
        entries[portId - 1] = null;
    }

    public Entry[] getEntries() {
        return entries;
    }

    public void setEntries(Entry[] entries) {
        this.entries = entries;
    }

    //getPortByMAC
}
