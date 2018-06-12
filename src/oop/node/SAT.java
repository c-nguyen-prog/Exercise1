package oop.node;

import oop.frame.structure.MAC;

/**
 * This class represents a SAT (Source Address Table)
 */
public class SAT {

    private Entry[] entries = null;
    private static SAT instance = null;

    private SAT(int numberOfPorts) {
        entries = new Entry[numberOfPorts];
    }

    /**
     * Method used to create the SAT object following Singleton pattern
     * @param numberOfPorts number of entries in SAT
     * @return single SAT instance
     */
    public static SAT createInstance(int numberOfPorts) {
        if (instance == null) {
            instance = new SAT(numberOfPorts);
        }
        return instance;
    }

    /**
     * Method used to return the single instance of SAT object
     * @return SAT instance
     */
    public static SAT getInstance() {
        return instance;
    }

    /**
     * Method used to add an entry in the SAT
     * @param mac MAC address
     * @param portId port ID
     */
    public void addEntry(MAC mac, int portId) {
        Entry entry = new Entry(mac, portId);
        entries[portId - 1] = entry;
    }

    /**
     * Method used to remove an entry in the SAT
     * @param portId port ID
     */
    public void removeEntry(int portId) {
        entries[portId - 1] = null;
    }

    /**
     * Getter method for array of entries in SAT
     * @return array of Entry
     */
    public Entry[] getEntries() {
        return entries;
    }
}
