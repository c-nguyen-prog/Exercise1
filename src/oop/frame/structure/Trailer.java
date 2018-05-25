package oop.frame.structure;

import java.nio.charset.StandardCharsets;

/**
 * This class represents a trailer
 */
public class Trailer {

    private String name;
    private byte[] bytes;

    /**
     * Contructor to create a trailer
     * @param name name of the trailer
     * @param bytes bytes data of the trailer
     */
    public Trailer (String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    /**
     * Getter method for name of the trailer
     * @return name of the trailer
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name of the trailer
     * @param name name of the trailer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for bytes data of the trailer
     * @return bytes data of the trailer
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Setter method for bytes data of the trailer
     * @param bytes bytes data of the trailer
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
