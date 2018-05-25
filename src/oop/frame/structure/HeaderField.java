package oop.frame.structure;

import java.nio.charset.StandardCharsets;

/**
 * This class represents a header field
 */
public class HeaderField {

    private String name;
    private byte[] bytes;

    /**
     * Constructor to create a header field
     * @param name name of the header field
     * @param bytes bytes data of the header field
     */
    public HeaderField (String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    /**
     * Getter method for name of the header field
     * @return name of the header field
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name of the header field
     * @param name name of the header field
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for the bytes data of the header field
     * @return bytes data of the header field
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Setter method for the bytes data of the header field
     * @param bytes bytes data of the header field
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
