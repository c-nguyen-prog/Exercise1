package oop.frame.structure;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

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
        for (int i = 0; i < ip.length; i++) {
            output += String.format("%x", ip[i] & 0xFF);
        }

        return output;
    }

    /**
     * Method used to return ip address in readable string format
     * @return ip address in IEEE format
     */
    public String toIntString() {
        InetAddress ip = null;
        try {
            ip = InetAddress.getByAddress(this.ip);
        } catch (Exception e) {

        }
        return ip.getHostAddress();
    }
}
