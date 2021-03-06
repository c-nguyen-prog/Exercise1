package oop.frame.structure;

/**
 * This class represents a media access control (MAC) address
 */
public class MAC {

    private byte[] reverseAdd;
    private byte[] normalAdd;

    /**
     * Constructor to create a MAC address
     * @param reverseAdd MAC address
     * @param normalAdd normal MAC address
     */
    public MAC (byte[] reverseAdd, byte[] normalAdd) {
        this.reverseAdd = reverseAdd;
        this.normalAdd = normalAdd;
    }

    /**
     * Getter method for reversed MAC address
     * @return reversed MAC address
     */
    public byte[] getReverseAdd() {
        return reverseAdd;
    }

    /**
     * Setter method for reversed MAC address
     * @param reverseAdd reversed MAC address
     */
    public void setReverseAdd(byte[] reverseAdd) {
        this.reverseAdd = reverseAdd;
    }

    /**
     * Getter method for normal MAC address
     * @return normal MAC address
     */
    public byte[] getNormalAdd() {
        return normalAdd;
    }

    /**
     * Setter method for normal MAC address
     * @param normalAdd normal MAC address
     */
    public void setNormalAdd(byte[] normalAdd) {
        this.normalAdd = normalAdd;
    }

    /**
     * Method used to output reversed MAC address as hex String
     * @return reversed MAC address as hex String
     */
    public String toHexString() {
        String output = "";
        for (int i = 0; i < reverseAdd.length; i++) {
            output += String.format("%02x", reverseAdd[i] & 0xFF);
        }
        return output;
    }

    /**
     * Method used to output MAC address in readable String
     * @return MAC address in IEEE format
     */
    public String toString() {
        String output = "";
        for (int i = 0; i < normalAdd.length - 1; i++) {
            output += String.format("%02X", normalAdd[i] & 0xFF);
            output += ":";
        }
        output += String.format("%02X", normalAdd[normalAdd.length - 1] & 0xFF);
        return output;
    }
}
