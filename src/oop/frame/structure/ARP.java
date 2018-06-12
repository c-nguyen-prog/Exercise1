package oop.frame.structure;

import java.nio.ByteBuffer;
import java.util.Observable;

/**
 * This class represents an arp message
 */

public class ARP extends Observable {
    private final byte[] hwType = new byte[]{(byte) 0x00, (byte) 0x01};
    private final byte[] protocolType = new byte[]{(byte) 0x08, (byte) 0x00};
    private final byte[] hwLength = new byte[]{(byte) 0x06};
    private final byte[] protocolLength = new byte[]{(byte) 0x04};
    private byte[] opcode;
    private MAC sourceMAC;
    private IPv4 sourceIP;
    private MAC destinationMAC;
    private IPv4 destinationIP;

    /**
     * Constructor to create a request ARP
     * @param sourceMAC source MAC address
     * @param sourceIP source IP address
     * @param destinationIP destination IP address
     */
    public ARP (MAC sourceMAC, IPv4 sourceIP, IPv4 destinationIP) {
        this.sourceMAC = sourceMAC;
        this.sourceIP = sourceIP;
        this.destinationIP = destinationIP;
        this.destinationMAC
                = new MAC(new byte[]{0,0,0,0,0,0}, new byte[]{0,0,0,0,0,0});
        opcode = new byte[]{(byte) 0x00, (byte) 0x01};
        setChanged();
        notifyObservers(this.toString());
    }


    /**
     * Constructor to create a reply ARP
     * @param sourceMAC source MAC address
     * @param sourceIP source IP address
     * @param destinationMAC destination MAC address
     * @param destinationIP destination IP address
     */
    public ARP (MAC sourceMAC, IPv4 sourceIP,
                MAC destinationMAC, IPv4 destinationIP) {
        this.sourceMAC = sourceMAC;
        this.sourceIP = sourceIP;
        this.destinationMAC = destinationMAC;
        this.destinationIP = destinationIP;
        opcode = new byte[]{(byte) 0x00, (byte) 0x02};
        setChanged();
        notifyObservers(this.toString());
    }

    /**
     * Getter method for hardware type variable
     * @return hardware type value (0001 for ethernet)
     */
    public byte[] getHwType() {
        return hwType;
    }

    /**
     * Getter method for protocol type
     * @return protocol type value (0800 for IPv4)
     */
    public byte[] getProtocolType() {
        return protocolType;
    }

    /**
     * Getter method for hardware length
     * @return hardware length value (06 for MAC)
     */
    public byte[] getHwLength() {
        return hwLength;
    }

    /**
     * Getter method for protocol length
     * @return protocol length value (04 for IPv4)
     */
    public byte[] getProtocolLength() {
        return protocolLength;
    }

    /**
     * Getter method for operation code
     * @return opcode (0001 for request, 0002 for reply)
     */
    public byte[] getOpcode() {
        return opcode;
    }

    /**
     * Getter method for source mac address
     * @return source mac address
     */
    public MAC getSourceMAC() {
        return sourceMAC;
    }

    /**
     * Setter method for source mac address
     * @param sourceMAC source mac address
     */
    public void setSourceMAC(MAC sourceMAC) {
        this.sourceMAC = sourceMAC;
    }

    /**
     * Getter method for source ip address
     * @return source ip address
     */
    public IPv4 getSourceIP() {
        return sourceIP;
    }

    /**
     * Setter method for source ip address
     * @param sourceIP source ip address
     */
    public void setSourceIP(IPv4 sourceIP) {
        this.sourceIP = sourceIP;
    }

    /**
     * Getter method for destination mac address
     * @return destination mac address
     */
    public MAC getDestinationMAC() {
        return destinationMAC;
    }

    /**
     * Setter method for destination mac address
     * @param destinationMAC destination mac address
     */
    public void setDestinationMAC(MAC destinationMAC) {
        this.destinationMAC = destinationMAC;
    }

    /**
     * Getter method for destination ip address
     * @return destination ip address
     */
    public IPv4 getDestinationIP() {
        return destinationIP;
    }

    /**
     * Setter method for destination ip address
     * @param destinationIP destination ip address
     */
    public void setDestinationIP(IPv4 destinationIP) {
        this.destinationIP = destinationIP;
    }

    /**
     * Method used to output bytes data as Hex String
     * @param bytes input bytes array
     * @return bytes data as Hex String
     */
    public String toHex(byte[] bytes) {
        String output = "";
        for (byte b : bytes) {
            output += String.format("%02x", b & 0xFF);
        }
        return output;
    }

    /**
     * Method used to return ARP's data bytes as an hexadecimal string
     * @return data bytes of ARP as string
     */
    public String toString() {
        String output = "";
        output = toHex(hwType) + toHex(protocolType) + toHex(hwLength)
                + toHex(protocolLength) + toHex(opcode) + sourceMAC.toString()
                + sourceIP.toString() + destinationMAC.toString()
                + destinationIP.toString();
        return output;
    }

    /**
     * Method used to return content of ARP as a byte array
     * @return ARP content as byte[] array
     */
    public byte[] toByteArray() {
        byte[] output = new byte[28];
        ByteBuffer byteBuffer = ByteBuffer.wrap(output);
        byteBuffer.put(hwType);
        byteBuffer.put(protocolType);
        byteBuffer.put(hwLength);
        byteBuffer.put(protocolLength);
        byteBuffer.put(opcode);
        byteBuffer.put(sourceMAC.getNormalAdd());
        byteBuffer.put(sourceIP.getIp());
        byteBuffer.put(destinationMAC.getNormalAdd());
        byteBuffer.put(destinationIP.getIp());
        return output;
    }
}

