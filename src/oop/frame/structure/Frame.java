package oop.frame.structure;

/**
 * This class represents a frame
 */
public class Frame {

    private String name;
    private Header header;
    private Payload payload;
    private Trailer trailer;

    /**
     * Constructor to create a frame with header, payload and trailer
     * @param name name of the frame
     * @param header header of the frame
     * @param payload payload of the frame
     * @param trailer trailer of the frame
     */
    public Frame(String name, Header header, Payload payload, Trailer trailer) {
        this.name = name;
        this.header = header;
        this.payload = payload;
        this.trailer = trailer;
    }

    /**
     * Construct to create a frame with header and payload
     * @param name name of the frame
     * @param header header of the frame
     * @param payload payload of the frame
     */
    public Frame(String name, Header header, Payload payload) {
        this.name = name;
        this.header = header;
        this.payload = payload;
    }

    /**
     * Getter method for name of the frame
     * @return name of the frame
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for name of the frame
     * @param name name of the frame
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for header of the frame
     * @return header of the frame
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Setter method for header of the frame
     * @param header header of the frame
     */
    public void setHeader(Header header) {
        this.header = header;
    }

    /**
     * Getter method for payload of the frame
     * @return payload of the frame
     */
    public Payload getPayload() {
        return payload;
    }

    /**
     * Setter method for payload of the frame
     * @param payload payload of the frame
     */
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    /**
     * Getter method for trailer of the frame
     * @return trailer of the frame
     */
    public Trailer getTrailer() {
        return trailer;
    }

    /**
     * Setter method for trailer of the frame
     * @param trailer trailer of the frame
     */
    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }

    /**
     * Method used to output a frame's bytes data as String
     * @return bytes data as String output
     */
    public String toString() {
        String bytes = "";

        bytes += header.toString();
        bytes += payload.toString();
        if (trailer != null) {
            bytes += trailer.toString();
        }

        return bytes;
    }

    /**
     * Method used to output a frame's bytes data as hex String
     * @return bytes data as hex String output
     */
    public String toHexString() {
        String bytes = "";

        bytes += header.toHexString();
        bytes += payload.toHexString();
        if (trailer != null) {
            bytes += trailer.toHexString();
        }

        return bytes;
    }

}
