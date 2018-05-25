package oop.frame.structure;

/**
 * This class represents an internet protocol version 4 (IPv4)
 */
public class IPv4 {
    private byte[] ip;

    /**
     * Constructor to create an IPv4
     * @param ip IP address
     */
    public IPv4(byte[] ip) {
        this.ip = ip;
    }

    /**
     * Getter method for IP address
     * @return ip address
     */
    public byte[] getIp() {
        return ip;
    }

    /**
     * Setter method for IP address
     * @param ip ip address
     */
    public void setIp(byte[] ip) {
        this.ip = ip;
    }

    /**
     * Method used to return ip address as hexadecimal string
     * @return ip address in hexadecimal
     */
    public String toString() {
        String output = "";
        for (byte ip : this.ip) {
            output += String.format("%02x", ip & 0xFF);
        }
        return output;
    }
}
