package oop.frame.structure;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * This class represents the ethernet frame
 */
public class Ethernet {
    private HeaderField destinationMAC;
    private HeaderField sourceMac;
    private HeaderField etherType;
    private HeaderField arp = null;
    private ArrayList<HeaderField> headerFields = new ArrayList<>();
    private Header header;
    private Payload payload;
    private Trailer trailer;
    private CRC32 crc;

    /**
     * Constructor to create an ethernet with mac addresses, ether type,
     * payload data bytes
     * @param destinationMAC header field destination mac address
     * @param sourceMAC header field source mac address
     * @param etherType header field ether type
     * @param bytes payload data bytes
     */
    public Ethernet (MAC destinationMAC, MAC sourceMAC,
                     byte[] etherType, byte[] bytes) {
        crc = new CRC32();
        this.destinationMAC = new HeaderField("DestinationMAC",
                destinationMAC.getReverseAdd());
        this.sourceMac = new HeaderField("SourceMAC", sourceMAC.getReverseAdd());
        this.etherType = new HeaderField("EtherType", etherType);
        this.headerFields.add(this.destinationMAC);
        this.headerFields.add(this.sourceMac);
        this.headerFields.add(this.etherType);
        this.header = new Header("Header", headerFields);
        this.payload = new Payload("Payload", bytes);
        byte[] crcBytes;
        if (bytes.length < 46) {
            crcBytes = new byte[60];
        } else {
            crcBytes = new byte[14 + bytes.length];
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(crcBytes);
        byteBuffer.put(destinationMAC.getNormalAdd());
        byteBuffer.put(sourceMAC.getNormalAdd());
        byteBuffer.put(etherType);
        byteBuffer.put(bytes);
        crc.update(crcBytes);
        this.trailer = new Trailer("Trailer", crc.getCRC());
    }

    /**
     * Constructor to create an ethernet from an arp
     * @param destinationMAC header field destination mac address
     * @param sourceMAC header field source mac address
     * @param arp ARP in the frame
     */
    public Ethernet (MAC destinationMAC, MAC sourceMAC, ARP arp) {
        crc = new CRC32();
        this.destinationMAC = new HeaderField("DestinationMAC",
                destinationMAC.getReverseAdd());
        this.sourceMac = new HeaderField("SourceMAC", sourceMAC.getReverseAdd());
        this.etherType = new HeaderField("Ethertype",
                new byte[]{(byte) 0x08, (byte) 0x06});
        this.arp = new HeaderField("ARP", arp.toByteArray()); //important line
        this.headerFields.add(this.destinationMAC);
        this.headerFields.add(sourceMac);
        this.headerFields.add(etherType);
        this.headerFields.add(this.arp);
        this.header = new Header("Header", headerFields);
        byte[] payload = new byte[18];
        for (int i = 0; i < payload.length; i++) {
            payload[i] = (byte) 0x00;
        }
        this.payload = new Payload("Payload", payload);
        //calculate crc
        byte[] crcBytes = new byte[60];
        ByteBuffer byteBuffer = ByteBuffer.wrap(crcBytes);
        byteBuffer.put(destinationMAC.getNormalAdd());
        byteBuffer.put(sourceMAC.getNormalAdd());
        byteBuffer.put(this.etherType.getBytes());
        byteBuffer.put(this.arp.getBytes());
        byteBuffer.put(this.payload.getBytes());
        crc.update(crcBytes);
        this.trailer = new Trailer("Trailer", crc.getCRC());
    }

    /**
     * Getter method for destination mac address header field
     * @return header field destination mac address
     */
    public HeaderField getDest() {
        return destinationMAC;
    }

    /**
     * Setter method for destination mac address header field
     * @param destinationMAC destination mac address header field
     */
    public void setDest(HeaderField destinationMAC) {
        this.destinationMAC = destinationMAC;
    }

    /**
     * Getter method for source mac address header field
     * @return source mac address header field
     */
    public HeaderField getSrc() {
        return sourceMac;
    }

    /**
     * Setter method for source mac address header field
     * @param sourceMac source mac address header field
     */
    public void setSrc(HeaderField sourceMac) {
        this.sourceMac = sourceMac;
    }

    /**
     * Getter method for ethertype
     * @return ethertype
     */
    public HeaderField getEtherType() {
        return etherType;
    }

    /**
     * Setter method for ethertype
     * @param type ethertype
     */
    public void setEtherType(HeaderField type) {
        this.etherType = type;
    }

    /**
     * Getter method for headerfields arraylist
     * @return headerfields arraylist
     */
    public ArrayList<HeaderField> getHeaderFields() {
        return headerFields;
    }

    /**
     * Setter method for headerfields arraylist
     * @param headerFields arraylist
     */
    public void setHeaderFields(ArrayList<HeaderField> headerFields) {
        this.headerFields = headerFields;
    }

    /**
     * Getter method for header
     * @return header
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Setter method for header
     * @param header header
     */
    public void setHeader(Header header) {
        this.header = header;
    }

    /**
     * Getter method for payload
     * @return payload
     */
    public Payload getPayload() {
        return payload;
    }

    /**
     * Setter method for payload
     * @param payload payload
     */
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    /**
     * Getter method for trailer
     * @return trailer
     */
    public Trailer getTrailer() {
        return trailer;
    }

    /**
     * Setter method for trailer
     * @param trailer trailer
     */
    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }

    /**
     * Getter method for CRC32
     * @return crc
     */
    public CRC32 getCrc() {
        return crc;
    }

    /**
     * Getter method for headerfield ARP
     * @return arp headerfield
     */
    public HeaderField getArp() {
        return arp;
    }

    /**
     * Getter method for headerfield ARP
     * @param arp arp headerfield
     */
    public void setArp(HeaderField arp) {
        this.arp = arp;
    }

    /**
     * Method used to output Ethernet frame bytes data as hex String
     * @return ethernet frame bytes data as hex String
     */
    public String toString() {
        String output = "";
        output += header.toHexString();
        String payloadHexOutput = payload.toHexString();
        if (arp != null) {
            while (payloadHexOutput.length() < 36) {
                payloadHexOutput += "0";
            }
        } else {
            while (payloadHexOutput.length() < 92) {
                payloadHexOutput += "0";
            }
        }
        output += payloadHexOutput;
        output += trailer.toHexString();
        return output;
    }
}
