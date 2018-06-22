package oop.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import oop.frame.structure.*;
import oop.model.Model;
import oop.node.Host;
import oop.node.Port;
import oop.node.Switch;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.out;

public class Controller {

    public static ArrayList<String> logOutPut = new ArrayList<>();
    public static String message;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane simAnchorPane;
    @FXML
    private TextField commandTextField;
    @FXML
    private ListView<String> logList;
    @FXML
    private ImageView host1;
    @FXML
    private ImageView host2;
    @FXML
    private ImageView host3;
    @FXML
    private ImageView switchImage;
    @FXML
    private Line line1;
    @FXML
    private Line line2;
    @FXML
    private Line line3;

    private Model model;
    private static final int CMD = 0;
    private ArrayList<HeaderField> headerFields = new ArrayList<>();
    private ArrayList<Header> headers = new ArrayList<>();
    private ArrayList<Payload> payloads = new ArrayList<>();
    private ArrayList<Trailer> trailers = new ArrayList<>();
    private ArrayList<Frame> frames = new ArrayList<>();
    private ArrayList<Host> hosts = new ArrayList<>();
    private ArrayList<Port> ports = new ArrayList<>();
    private Switch aSwitch;
    private HashMap<Integer, Host> connection = new HashMap<Integer, Host>();
    private ArrayList<String> help = new ArrayList<>();


    MenuItem connectToSwitch = new MenuItem("Connect to switch");
    MenuItem sendEthernet = new MenuItem("Send an Ethernet frame");
    MenuItem sendArp = new MenuItem("Send an ARP frame");
    MenuItem removeHost = new MenuItem("Remove host from switch");
    MenuItem getsat = new MenuItem("Show MAC Address Table");
    ContextMenu contextMenuSwitch = new ContextMenu(getsat);
    ContextMenu contextMenuConnect = new ContextMenu(connectToSwitch);
    ContextMenu contextMenuRemove = new ContextMenu(removeHost, sendEthernet, sendArp);
    boolean connected1 = false, connected2 = false, connected3 = false;


    @FXML
    public void initialize() {
        model = Model.getInstance();
        createHost("Host1", "AA:BB:CC:DD:EE:FF", "192.168.0.1");
        createHost("Host2", "FF:EE:CC:DD:BB:AA", "192.168.0.2");
        createHost("Host3", "00:11:22:33:44:55", "192.168.0.3");
        createSwitch("3");
    }

    @FXML
    public void host1Context(MouseEvent mouseEvent) {
        if (!connected1) {
            contextMenuConnect.show(simAnchorPane, mouseEvent.getScreenX(), mouseEvent.getScreenY());
            connectToSwitch.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    out.println("connect switch host 1");
                    addHost("Host1", "1");
                    line1.setVisible(true);
                    connected1 = true;
                }
            });
        } else {
            contextMenuRemove.show(simAnchorPane, mouseEvent.getScreenX(), mouseEvent.getScreenY());
            removeHost.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    removeHost("1");
                    line1.setVisible(false);
                    connected1 = false;
                }
            });
            sendEthernet.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    sendETH("Host1", "FF:EE:CC:DD:BB:AA", "Hello this is host1");
                }
            });
            sendArp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    sendARP("Host1", "192.168.0.2");
                }
            });
        }
    }

    @FXML
    public void host2Context(MouseEvent mouseEvent) {
        if (!connected2) {
            contextMenuConnect.show(simAnchorPane, mouseEvent.getScreenX(), mouseEvent.getScreenY());
            connectToSwitch.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    addHost("Host2", "2");
                    line2.setVisible(true);
                    connected2 = true;
                }
            });
        } else {
            contextMenuRemove.show(simAnchorPane, mouseEvent.getScreenX(), mouseEvent.getScreenY());
            removeHost.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    removeHost("2");
                    line2.setVisible(false);
                    connected2 = false;
                }
            });
            sendEthernet.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    sendETH("Host2", "AA:BB:CC:DD:EE:FF", "Hello this is host2");
                }
            });
            sendArp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    sendARP("Host2", "192.168.0.3");
                }
            });
        }
    }

    @FXML
    public void host3Context(MouseEvent mouseEvent) {
        if (!connected3) {
            contextMenuConnect.show(simAnchorPane, mouseEvent.getScreenX(), mouseEvent.getScreenY());
            connectToSwitch.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    addHost("Host3", "3");
                    line3.setVisible(true);
                    connected3 = true;
                }
            });
        } else {
            contextMenuRemove.show(simAnchorPane, mouseEvent.getScreenX(), mouseEvent.getScreenY());
            removeHost.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    removeHost("3");
                    line3.setVisible(false);
                    connected3 = false;
                }
            });
            sendEthernet.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    out.println("Sent an ethernet");
                    sendETH("Host3", "FF:FF:FF:FF:FF:FF", "Hello this is host3 broadcasting");
                }
            });
            sendArp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    sendARP("Host3", "192.168.0.1");
                }
            });
        }
    }

    @FXML
    public void switchContext(MouseEvent mouseEvent) {
        contextMenuSwitch.show(simAnchorPane, mouseEvent.getScreenX(),mouseEvent.getScreenY());
        getsat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getSAT();
            }
        });
    }


    @FXML
    public void processCommand(ActionEvent event) {
        String text = commandTextField.getText();
        String[] data = text.split("\\s+");

        if (data[CMD].equals("help")) {
            logList.setItems(model.getHelpList());

        } else if (data[CMD].equals("createHeaderField")) {
            if (data.length == 3) {
                createHeaderField(data[1], data[2]);
            } else {
                error("Expected syntax: createHeaderField "
                        + "<headerFieldName> <String>");
            }

        } else if (data[CMD].equals("createHeader")) {
            if (data.length > 2) {
                ArrayList<String> hfnames = new ArrayList<>();
                for (int i = 2; i < data.length; i++) {
                    hfnames.add(data[i]);
                }
                createHeader(data[1], hfnames);
            } else {
                error("Expected syntax: createHeader <headerName> "
                        + "<headerFieldName1> ... <headerFieldNameN>");
            }

        } else if (data[CMD].equals("createPayload")) {
            if (data.length == 3) {
                createPayload(data[1], data[2]);
            } else {
                error("Expected syntax: createPayload <payloadName> "
                        + "<String|frameName>");
            }

        } else if (data[CMD].equals("createTrailer")) {
            if (data.length == 3) {
                createTrailer(data[1], data[2]);
            } else {
                error("Expected syntax: createTrailer <trailerName> <String>");
            }

        } else if (data[CMD].equals("createFrame")) {
            if (data.length == 4) {
                createFrame(data[1], data[2], data[3], "0");
            } else if (data.length == 5) {
                createFrame(data[1], data[2], data[3], data[4]);
            } else {
                error("Expected syntax: createFrame <frameName> "
                        + "<headerName> <payloadName> (<trailerName>)");
            }

        } else if (data[CMD].equals("printFrame")) {
            if (data.length == 2) {
                printFrame(10, data[1]);
            } else {
                error("Expected syntax: printFrame <frameName>");
            }

        } else if (data[CMD].equals("printFrameHex")) {
            if (data.length == 2) {
                printFrame(16, data[1]);
            } else {
                error("Expected syntax: printFrameHex <frameName>");
            }

        } else if (data[CMD].equals("createIP")) {
            if (data.length == 2) {
                createIP(data[1]);
            } else {
                error("Expected syntax: createIP <ip address>");
            }

        } else if (data[CMD].equals("createMAC")) {
            if (data.length == 2) {
                createMAC(data[1]);
            } else {
                error("Expected syntax: createMAC <mac address>");
            }

        } else if (data[CMD].equals("createETH")) {
            if (data.length == 5) {
                createETH(data[1], data[2], data[3], data[4]);
            } else if (data.length < 5) {
                error("Too few arguments");
            } else {
                error("Too many arguments");
            }
        } else if (data[CMD].equals("createARP")) {
            if (data.length == 4) {
                createARP(data[1], data[2], "0", data[3]);
            } else if (data.length == 5) {
                createARP(data[1], data[2], data[3], data[4]);
            } else {
                error("Too few arguments");
            }

        } else if (data[CMD].equals("createHost")) {
            if (data.length == 4) {
                createHost(data[1], data[2], data[3]);
            } else {
                error("Expected syntax: createHost <host name> <MAC> <IP>");
            }

        } else if (data[CMD].equals("sendETH")) {
            if (data.length == 4) {
                sendETH(data[1], data[2], data[3]);
            } else {
                error("Expected syntax: sendETH <hostName> <destMac> <payload>");
            }

        } else if (data[CMD].equals("createSwitch")) {
            if (data.length == 2) {
                createSwitch(data[1]);
            } else {
                error("Expected syntax: createSwitch <numberOfPorts>");
            }

        } else if (data[CMD].equals("addHost")) {
            if (data.length == 3) {
                addHost(data[1], data[2]);
            } else {
                error("Expected syntax: addHost <hostName> <portID>");
            }

        } else if (data[CMD].equals("removeHost")) {
            if (data.length == 2) {
                removeHost(data[1]);
            } else {
                error("Expected syntax: removeHost <portID>");
            }

        } else if (data[CMD].equals("getSAT")) {
            if (data.length == 1) {
                getSAT();
            }

        } else if (data[CMD].equals("sendARP")) {
            if (data.length == 3) {
                sendARP(data[1], data[2]);
            } else {
                error("Expected syntax: sendARP <hostName> <destination IP>");
            }

        } else {
            error("Command not found! Type 'help' for list of commands");
        }
    }

    /**
     * Method used to print out syntax error message when user inputs incorrectly
     * @param message error message to be printed
     */
    public void error(String message) {
        ObservableList<String> errorOutput;
        String error = "Error! " + message;
        logOutPut.add(error);
        errorOutput = FXCollections.observableArrayList(logOutPut);
        logList.setItems(errorOutput);
    }

    public void outPrint(String string) {
        ObservableList<String> output;
        logOutPut.add(string);
        output = FXCollections.observableArrayList(logOutPut);
        logList.setItems(output);
    }

    /**
     * Method used to create a header field with given name and
     * String input as bytes data
     * @param hfname name of the header field to be created
     * @param bytes bytes data of the header field to be created
     */
    public void createHeaderField(String hfname, String bytes) {
        int length = bytes.length();
        HeaderField headerField = new HeaderField(hfname, bytes.getBytes());
        headerFields.add(headerField);
        outPrint("headerField created with " + length + " bytes");
    }

    /**
     * Method used to create a header with given name and header field(s)
     * <p>
     * It checks if the input header fields are existing, then create
     * a header from them, if not output error
     * @param hname name of the header to be created
     * @param hfnames header fields in the header to be created
     */
    public void createHeader(String hname, ArrayList<String> hfnames) {
        boolean condition = true;

        //checks if input hfnames matches with existing hfnames
        for (String hfname : hfnames) {
            if (headerFields.size() != 0) {
                boolean match = false;

                //input name is compared with each header field
                for (HeaderField headerField : headerFields) {
                    if (hfname.equals(headerField.getName()))  {
                        match = true;
                    }
                }

                if (!match) {
                    outPrint("HeaderField " + hfname + " does not exist");
                    condition = false;
                }
            } else {
                outPrint("HeaderField " + hfname + " does not exist");
                condition = false;
            }
        }

        //if all input names are correct then create header
        if (condition) {
            ArrayList<HeaderField> newHeaderFields = new ArrayList<>();
            for (String hfname : hfnames) {
                for (HeaderField headerField : headerFields) {
                    if (hfname.equals(headerField.getName()))  {
                        newHeaderFields.add(headerField);
                    }
                }
            }
            Header header = new Header(hname, newHeaderFields);
            headers.add(header);
            outPrint("header created out of " + newHeaderFields.size()
                    + " headerFields");
        }
    }

    /**
     * Method used to create a payload with given name and String input.
     * <p>
     * If the String is the name of a frame, a payload is created
     * as a frame instead.
     * @param pname name of the payload to be created
     * @param bytes string input as bytes data or frame name
     */
    public void createPayload(String pname, String bytes) {
        boolean payloadIsFrame = false;

        // checks if input is an existing frame
        // if yes creates frame as payload
        if (frames.size() != 0) {
            for (Frame frame : frames) {
                if (bytes.equals(frame.getName())) {
                    payloadIsFrame = true;
                    Payload payload = new Payload(pname,
                            frame.toString().getBytes());
                    payloads.add(payload);
                    outPrint("payload created");
                }
            }
        }

        //if not creates payload with input as bytes
        if (!payloadIsFrame) {
            Payload payload = new Payload(pname, bytes.getBytes());
            payloads.add(payload);
            outPrint("payload created");
        }
    }

    /**
     * Method used to create a trailer with given name and
     * String input as bytes data
     * @param tname name of the trailer to be created
     * @param bytes bytes data of the trailer to be created
     */
    public void createTrailer(String tname, String bytes) {
        Trailer trailer = new Trailer(tname, bytes.getBytes());
        trailers.add(trailer);
        outPrint("trailer created");
    }


    /**
     * Method used to create frame with name, header, payload, (trailer)
     * @param fname name of the frame to be created
     * @param hname header in the frame to be created
     * @param pname payload in the frame to be created
     * @param tname trailer in the frame to be created
     */
    public void createFrame(String fname, String hname, String pname, String tname) {
        Header header = null;
        Payload payload = null;
        Trailer trailer = null;
        boolean condition = true;
        boolean matchHeader = false, matchPayload = false, matchTrailer = false;

        //header, payload and trailer must already existed
        if (headers.size() == 0) {
            outPrint("Header " + hname + " does not exist");
            return;
        } else if (payloads.size() == 0) {
            outPrint("Payload " + pname + " does not exist");
            return;
        } else if (!tname.equals("0") && trailers.size() == 0) {
            outPrint("Trailer " + tname + " does not exist");
            return;
        }

        //tests if header, payload, trailer name match with database
        for (int i = 0; i < headers.size(); i++) {
            for (int j = 0; j < payloads.size(); j++) {
                if (!tname.equals("0")) {
                    for (int k = 0; k < trailers.size(); k++) {
                        if (hname.equals(headers.get(i).getName())) {
                            header = headers.get(i);
                            matchHeader = true;
                            if (pname.equals(payloads.get(j).getName())) {
                                payload = payloads.get(j);
                                matchPayload = true;
                                if (tname.equals(trailers.get(k).getName())) {
                                    trailer = trailers.get(k);
                                    matchTrailer = true;
                                }
                            }
                        }
                    }
                } else {
                    if (hname.equals(headers.get(i).getName())) {
                        header = headers.get(i);
                        matchHeader = true;
                        if (pname.equals(payloads.get(j).getName())) {
                            payload = payloads.get(j);
                            matchPayload = true;
                        }
                    }
                }
            }
        }

        //output error message if name doesn't match
        if (!matchHeader) {
            condition = false;
            outPrint("Header " + hname + " does not exist");
        } else if (!matchPayload) {
            condition = false;
            outPrint("Payload " + pname + " does not exist");
        } else if (!matchTrailer && !tname.equals("0")) {
            condition = false;
            outPrint("Trailer " + tname + " does not exist");
        }

        //if names match, create new frame with trailer
        if (condition) {
            Frame frame = new Frame(fname, header, payload, trailer);
            frames.add(frame);
            outPrint("frame created");
        }
    }

    /**
     * Method used to print out frame bytes data in console
     * @param type 10 for normal string, 16 for hexadecimal
     * @param fname name of the frame
     */
    public void printFrame(int type, String fname) {
        boolean match = false;
        if (frames.size() != 0) {
            for (Frame frame : frames) {
                if (fname.equals(frame.getName())) {
                    match = true;
                    if (type == 10) {
                        outPrint(frame.toString());
                    } else {
                        outPrint(frame.toHexString());
                    }
                }
            }
            if (!match) {
                outPrint("Frame " + fname + " does not exist");
            }
        } else {
            outPrint("Frame " + fname + " does not exist");
        }
    }

    /*
    public void printFrameHex(String fname) {
        printFrame(16, fname);
    }
    */

    /**
     * Method creates an IPv4 object from a String input
     * @param ip input String IP address
     * @return the newly created IPv4 object
     */
    public IPv4 makeIP(String ip) {
        IPv4 ipv4 = null;
        try {
            InetAddress address = InetAddress.getByName(ip);
            ipv4 = new IPv4(address.getAddress());
        } catch (Exception e) {
            error("IP Address is not well formed");
        }
        return ipv4;
    }

    /**
     * Method used to create an IPv4 from user input
     * @param ip input IP address
     */
    public void createIP(String ip) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            IPv4 ipv4 = new IPv4(address.getAddress());
            outPrint("IPv4 Address: " + address.getHostAddress());
        } catch (Exception e) {
            error("IP Address is not well formed");
        }
    }

    /**
     * Method creates a mac address object from an input String
     * <p>
     * Input MAC is checked if the MAC format is valid MAC is split into
     * 6 octets, each octet is converted to 2-complement binary and then to
     * decimal. This is saved as normal MAC address.
     * After being converted to 2-complement, the binary is reversed. The resulting
     * reversed-bit binary is converted to binary and to be saved in ethernet
     * @param mac input String mac address
     * @return the newly created MAC address object
     */
    public MAC makeMAC(String mac) {
        boolean condition = true;
        String[] octet = mac.split("\\W");
        byte[] reverseMAC = new byte[6];
        byte[] decMAC = new byte[6];
        MAC macAddress = null;

        try {
            if (octet.length == 6) {
                for (int i = 0; i < octet.length; i++) {
                    if (octet[i].length() != 2) {
                        condition = false;

                    } else if (octet[i].toUpperCase().compareTo("FF") > 0) {
                        condition = false;

                    } else {
                        //change to binary from hex
                        //hex string -> int -> binary int -> binary string
                        String binaryMAC = String.format("%08d",
                                Integer.parseInt(Integer.toBinaryString(
                                        Integer.parseInt(octet[i], 16))));
                        //change to decimal and save to decMac byte[]
                        //binary string -> a byte
                        decMAC[i] =  (byte) Long.parseLong(binaryMAC, 2);
                        //reverse bit
                        char[] binaryMACChars = binaryMAC.toCharArray();
                        for (int j = 0; j < binaryMACChars.length / 2; j++) {
                            char temp = binaryMACChars[j];
                            binaryMACChars[j] =
                                    binaryMACChars[binaryMACChars.length - j - 1];
                            binaryMACChars[binaryMACChars.length - j - 1] = temp;
                        }
                        //save reversed octet binary as reversed binary
                        binaryMAC = new String(binaryMACChars);
                        reverseMAC[i] = (byte) Long.parseLong(binaryMAC, 2);
                    }
                }
            } else {
                condition = false;
            }
        } catch (Exception e) {
            condition = false;
        }

        if (condition) {
            macAddress = new MAC(reverseMAC, decMAC);
            return macAddress;
        } else {
            error("MAC address must contain 12 hex digits");
        }
        return null;
    }

    /**
     * Method used to create a mac address from user input
     * @param mac input mac address
     */
    public void createMAC(String mac) {
        if (makeMAC(mac) != null) {
            MAC macAddress = makeMAC(mac);
            outPrint("MAC Address: " + macAddress.toString());
        }
    }

    /**
     * Method used to create an ethernet from destination MAC address, source MAC
     * address, ether type and payload bytes data
     * @param destAddress destination MAC address
     * @param srcAddress source MAC address
     * @param etherType ether type variable
     * @param data payload bytes data
     */
    public void createETH(String destAddress, String srcAddress,
                          String etherType, String data) {

        MAC dest = makeMAC(destAddress);
        MAC src = makeMAC(srcAddress);
        byte[] type = new byte[2];

        if (etherType.equals("IPv4")) {
            type = new byte[]{(byte) 0x08, (byte) 0x00};
        } else if (etherType.equals("ARP")) {
            type = new byte[]{(byte) 0x08, (byte) 0x06};
        } else if (etherType.equals("IPv6")) {
            type = new byte[]{(byte) 0x86, (byte) 0xdd};
        }

        Ethernet ethernet = new Ethernet(dest, src, type, data.getBytes());
        outPrint("Frame: " + ethernet.toString());
    }

    /**
     * Method used to create an ARP
     * @param sourceMAC source MAC address
     * @param sourceIP source IP address
     * @param destinationMAC destination MAC address
     * @param destinationIP destination IP address
     */
    public void createARP(String sourceMAC, String sourceIP,
                          String destinationMAC, String destinationIP) {

        try {
            ARP arp = null;
            MAC destMAC = null;
            MAC srcMAC = makeMAC(sourceMAC);

            InetAddress address = InetAddress.getByName(sourceIP);
            IPv4 srcIP = new IPv4(address.getAddress());

            address = InetAddress.getByName(destinationIP);
            IPv4 destIP = new IPv4(address.getAddress());

            if (destinationMAC.equals("0")) {
                destMAC = makeMAC("FF:FF:FF:FF:FF:FF");
                arp = new ARP(srcMAC, srcIP, destIP);
            } else {
                destMAC = makeMAC(destinationMAC);
                arp = new ARP(srcMAC, srcIP, destMAC, destIP);
            }

            Ethernet ethernet = new Ethernet(destMAC, srcMAC, arp);
            outPrint("Frame: " + ethernet.toString());
        } catch (Exception e) {
            out.print("");
        }
    }

    /**
     * Method used to create a host
     * @param hostName name of the host
     * @param macAddress MAC address of the host
     * @param ipAddress IP address of the host
     */
    public void createHost(String hostName, String macAddress, String ipAddress) {
        MAC mac = makeMAC(macAddress);
        IPv4 ip = makeIP(ipAddress);
        Host host = new Host(hostName, mac, ip);
        hosts.add(host);
        outPrint(hostName + " created");
    }

    /**
     * Method used to create a switch
     * @param number number of ports in the switch
     */
    public void createSwitch(String number) {
        int numberOfPorts = convertToInt(number);
        aSwitch = new Switch(numberOfPorts);
        outPrint("Switch with " + number + " ports created");
    }

    /**
     * Method used to add a host to the switch
     * @param hostName name of the host
     * @param port port the host connects to
     */
    public void addHost(String hostName, String port) {
        Host host = getHostFromString(hostName);
        if (host != null) {
            int portId = convertToInt(port);
            Port p = aSwitch.getPort(portId);
            ports.add(p);
            host.addObserver(p);
            p.addObserver(host);
            connection.put(p.getPortId(), host);
            outPrint(hostName + " connected to port " + port);
        }
    }

    /**
     * Method used to remove a host from the switch
     * @param portNr port number that the host connected to
     */
    public void removeHost(String portNr) {
        Port port = null;
        Host host = null;
        int portId = convertToInt(portNr);
        for (Port p : ports) {
            if (p.getPortId() == portId) {
                port = p;
            }
        }
        if (connection.containsKey(portId)) {
            host = (Host) connection.get(portId);
            connection.remove(portId);
        }
        port.deleteObserver(host);
        host.deleteObserver(port);
        outPrint(host.getName() + " removed from port " + portId);
    }

    /**
     * Method used to send ethernet from a host to a MAC address
     * @param hostName name of the host
     * @param destinationMAC destination MAC address
     * @param payload payload message
     */
    public void sendETH(String hostName, String destinationMAC, String payload) {
        MAC destMAC = makeMAC(destinationMAC);
        Host host = getHostFromString(hostName);
        MAC srcMAC = host.getMac();
        byte[] etherType =  new byte[]{(byte) 0x08, (byte) 0x00};
        Ethernet ethernet =
                new Ethernet(destMAC, srcMAC, etherType, payload.getBytes());
        host.sendETHFrame(ethernet);
        outPrint("");
    }

    /**
     * Method used to get the SAT
     */
    public void getSAT() {
        if (!aSwitch.getSAT().equals("")) {
            outPrint(aSwitch.getSAT());
        } else {
            outPrint("MAC Address Table is empty");
        }
    }

    /**
     * Method used to send an ARP to an IP address
     * @param hostName name of the host
     * @param destIP destination IP address
     */
    public void sendARP(String hostName, String destIP) {
        IPv4 destinationIP = makeIP(destIP);
        Host host = getHostFromString(hostName);
        ARP arp = new ARP(host.getMac(), host.getIp(), destinationIP);
        host.sendARPFrame(arp);
        outPrint(message);
    }

    private int convertToInt(String port) {
        int portNr = 0;
        try {
            portNr = Integer.parseInt(port);
        } catch (Exception e) {
            error("port number must be an integer!");
        }
        return portNr;
    }

    private Host getHostFromString(String hostName) {
        Host host = null;
        if (hosts.size() != 0) {
            for (Host h : hosts) {
                if (hostName.equalsIgnoreCase(h.getName())) {
                    host = h;
                }
            }
        }
        return host;
    }

    /**
     * Setter method for the reply message of a host
     * @param string host message
     */
    public static void setMessage(String string) {
        message = string;
    }


    public ImageView getHost1() {
        return host1;
    }

    public ImageView getHost2() {
        return host2;
    }

    public ImageView getHost3() {
        return host3;
    }

    public ImageView getSwitchImage() {
        return switchImage;
    }
}
