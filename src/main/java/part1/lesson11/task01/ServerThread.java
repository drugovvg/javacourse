package part1.lesson11.task01;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

/**
 * This class implements a thread and some methods to send and receive
 * messages to specific clients of the server with using of a predefined protocol.
 */
public class ServerThread extends Thread {

    /*
    List of codes for our own protocol.
     */
    public final static String CODE_HELLO = "[10]";
    public final static String CODE_HELLO_RESPONSE_NAME_DUPLICATE = "[11]";
    public final static String CODE_HELLO_SUCCESS = "[12]";
    public final static String CODE_HELLO_INITIATE = "[13]";
    public final static String CODE_BROADCAST_MESSAGE = "[20]";
    public final static String CODE_BYE_TO_SERVER = "[30]";

    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean mainCycleFlag = true;
    protected HashMap<Integer, Client> clientsAddressMap = new HashMap<>();
    protected HashMap<String, Client> clientsNameMap = new HashMap<>();

    /**
     * Constructor
     * @throws IOException
     */
    public ServerThread() throws IOException {
        this("ServerThread");
    }

    /**
     * Constructor
     * @param name thread name
     * @throws IOException
     */
    public ServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);
    }

    public void run() {
        while (mainCycleFlag) {
            try {
                byte[] buf = new byte[256];

                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                String s = new String(packet.getData(), 0, packet.getLength());
                Client aClient = new Client(
                        address,
                        port
                );

                //System.out.println(s);

                /*
                Process the case where a client sends us its nickname.
                We should save its in the list of users, so we can detect
                name of a user by its address, check if someone tries to use the
                nickname etc.
                 */
//                if (s.startsWith(CODE_HELLO) && !clientsAddressMap.containsKey(aClient.hashCode())) {
                if (s.startsWith(CODE_HELLO)) {
                    String userName = s.substring(CODE_HELLO.length());
                    if (clientsNameMap.containsKey(userName)) { // Check if we already have a client with the same name.
                        String responseMessage = CODE_HELLO_RESPONSE_NAME_DUPLICATE + userName + " already in use. Please choose another nickname.";
                        sendMessage(responseMessage, aClient.getAddress(), aClient.getPort());
                    } else { // Successful registration and adding to the user list.
                        Client value = new Client(
                                s.substring(CODE_HELLO.length()),
                                address,
                                port
                        );
                        clientsAddressMap.put(aClient.hashCode(), value);
                        clientsNameMap.putIfAbsent(userName, value);
                        String responseMessage = CODE_HELLO_SUCCESS + userName + ", welcome to the server. Press enter to send your first message.";
                        sendMessage(responseMessage, aClient.getAddress(), aClient.getPort());
                    }

                /*
                This server receives a regular or direct message.
                */
                } else if (s.startsWith(CODE_BROADCAST_MESSAGE) && clientsAddressMap.containsKey(aClient.hashCode())) {

                    Client fromClient = clientsAddressMap.get(aClient.hashCode());
                    String message = s.substring(CODE_BROADCAST_MESSAGE.length());

                    if (message.length() > 0) { // do not send the message if it is empty

                        // That is how we detect if it is a direct message.
                        String nameTo = StringUtils.substringBetween(message, "@", ",");
                        if (nameTo != null) {
                            if (clientsNameMap.containsKey(nameTo)) {
                                Client toClient = clientsNameMap.get(nameTo);
                                sendMessage(fromClient.getName() + " (privately): " + message,
                                        toClient.getAddress(),
                                        toClient.getPort());
                            } else {
                                sendMessage("There is no user with such name: " + nameTo,
                                        fromClient.getAddress(),
                                        fromClient.getPort());
                            }

                        } else {

                            // in case is just a broadcast message it sends to everyone except the author of the message
                            clientsAddressMap.forEach((k, v) -> {
                                if (k != aClient.hashCode()) {
                                    String forecastMessage = fromClient.getName() + ": " + message;
                                    sendMessage(forecastMessage, v.getAddress(), v.getPort());
                                }
                            });
                        }
                    }

                /*
                This is just a confirmation for a client at the very first connection that he can initiate
                a hello protocol (send his name).
                 */
                } else if (!clientsAddressMap.containsKey(aClient.hashCode())) {
                    sendMessage(CODE_HELLO_INITIATE, aClient.getAddress(), aClient.getPort());
                    clientsAddressMap.put(aClient.hashCode(), aClient);

                /*
                A client can send us a bye message when, so we can free up his name for new clients.
                 */
                } else if (s.startsWith(CODE_BYE_TO_SERVER) && clientsAddressMap.containsKey(aClient.hashCode())) {
                    Client removedClient = clientsAddressMap.remove(aClient.hashCode());
                    clientsNameMap.remove(removedClient.getName());
                }

            } catch (IOException e) {
                e.printStackTrace();
                mainCycleFlag = false;
            }
        }
        socket.close();
    }

    /**
     * Send message to a client
     * @param responseMessage response message
     * @param address client's hostname
     * @param port client's port
     */
    protected void sendMessage(String responseMessage, InetAddress address, int port) {
        byte[] message = responseMessage.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(message, message.length, address, port);
        try {
            socket.send(responsePacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
