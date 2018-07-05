package oop.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.Observable;

public class Model {

    private ObservableList<String> helpList;
    private static Model instance = null;

    private Model() {
        String[] help = new String[] {"createHeaderField <hfname String> create a header field",
                "createHeader <hname hfname1 .. hfnameN> create a header",
                "createPayload <pname String|fname> create a payload",
                "createTrailer <tname String> create a trailer",
                "createFrame <fname hname pname (tname)> create a frame",
                "printFrame <fname> print out data bytes in a frame",
                "printFrameHex <fname> print out data bytes as hex format",
                "createIP <IP address> create an IP address",
                "createMAC <MAC address> create a MAC address",
                "createETH <destinationMAC sourceMAC ethertype bytes> create an ethernet II",
                "createARP <sourceMAC sourceIP (destinationMAC) destinationIP> create an ARP frame",
                "createHost <hostName MAC IP> create a host",
                "createSwitch <numberOfPorts> create a switch",
                "addHost <hostName portID> add host to a port",
                "removeHost <portID> remove host from a port",
                "sendETH <hostName destinationMAC payload> send ethernet frame to MAC address",
                "sendARP <hostName destinationIP> send ARP frame to IP address"};
        helpList = FXCollections.observableArrayList(help);
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public ObservableList<String> getHelpList() {
        return helpList;
    }

}
