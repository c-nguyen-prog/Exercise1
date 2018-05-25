package oop.frame.structure;

import java.nio.charset.StandardCharsets;

/**
 * This class represents a payload
 */
public class Payload {

    private String name;
    private byte[] bytes;

    /**
     * Constructor to create a payload
     * @param name name of the payload
     * @param bytes data bytes of the payload
     */
    public Payload (String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    /**
     * Getter method for name of the payload
     * @return name of the payload
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name of the payload
     * @param name name of the payload
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for bytes data of the payload
     * @return bytes data of the payload
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Setter method for bytes data of the payload
     * @param bytes bytes data of the payload
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * Method used to output bytes data as String
     * @return bytes data as String
     */
    public String toString() {
        String output = new String(bytes, StandardCharsets.UTF_8);
        return output;
    }

    /**
     * Method used to output bytes data as hex String
     * @return bytes data as hex String
     */
    public String toHexString() {
        String output = "";
        for (byte b : bytes) {
            output += String.format("%02x", b & 0xFF);
        }
        return output;
    }
}
