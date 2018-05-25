package oop.frame.structure;

import java.util.ArrayList;

/**
 * This class represents a header
 */
public class Header {

    private String name;
    private ArrayList<HeaderField> headerField;

    /**
     * Constructor to create a header
     * @param name name of the header
     * @param headerField an array list of header fields in the header
     */
    public Header (String name, ArrayList<HeaderField> headerField) {
        this.name = name;
        this.headerField = headerField;
    }

    /**
     * Getter method for name of the header
     * @return name of the header
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name of the header
     * @param name name of the header
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for header fields in the header
     * @return an array list of header fields in the header
     */
    public ArrayList<HeaderField> getHeaderField() {
        return headerField;
    }

    /**
     * Setter method for header fields in the header
     * @param headerField an array list of header fields in the header
     */
    public void setHeaderField(ArrayList<HeaderField> headerField) {
        this.headerField = headerField;
    }

    /**
     * Method used to output bytes data as String
     * @return bytes data as String
     */
    public String toString() {
        String output = "";
        for (HeaderField headerField : headerField) {
            output += headerField.toString();
        }
        return output;
    }

    /**
     * Method used to output bytes data as hex String
     * @return bytes data as hex String
     */
    public String toHexString() {
        String output = "";
        for (HeaderField headerField : headerField) {
            output += headerField.toHexString();
        }
        return output;
    }
}
