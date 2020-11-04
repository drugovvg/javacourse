package part1.lesson11.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a thread and some methods to send and receive
 * messages to specific chat server with using of a predefined protocol.
 */
public class ClientThread extends Thread {

    protected DatagramSocket socket = null;
    protected String serverHostname = "localhost";
    protected boolean printServerMessages = true;
    protected boolean consoleInputEnabled = false;
    protected List<String> bufServerMessages = new ArrayList<>();

    /**
     * Constructor
     *
     * @param hostname hostname of the server you want to connect with
     * @throws IOException
     */
    public ClientThread(String hostname) throws IOException {
        this("ClientThread", hostname);
    }

    /**
     * Constructor
     *
     * @param name     thread name
     * @param hostname hostname of the server you want to connect with
     * @throws IOException
     */
    public ClientThread(String name, String hostname) throws IOException {
        super(name);
        socket = new DatagramSocket();
        serverHostname = hostname;
    }

    public void run() {
        while (true) {
            byte[] buf = new byte[256];

            // Initiate connection to the server
            InetAddress address = null;
            try {
                address = InetAddress.getByName(serverHostname);
            } catch (UnknownHostException e) {
                setConsoleInputDisabled();
                System.out.println("Please check server's host name again.");
                break;
            }

            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);

            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Get response
            packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String received = new String(packet.getData(), 0, packet.getLength());
//            System.out.println(received);

            /*
            In case we have a user entering something in the console we should pause printing
            received messages, put them into a buffer and print it later.
            */
            if (printServerMessages) {

                try {
                    processServerMessage(received);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                bufServerMessages.add(received);
            }

        }
    }

    /**
     * Process server messages in according to the protocol.
     * @param message server message
     * @throws IOException
     */
    private void processServerMessage(String message) throws IOException {
        if (message.startsWith(ServerThread.CODE_HELLO_RESPONSE_NAME_DUPLICATE)) {
            System.out.println(message.substring(ServerThread.CODE_HELLO_RESPONSE_NAME_DUPLICATE.length()));
            invokeHelloDialogue();
        } else if (message.startsWith(ServerThread.CODE_HELLO_INITIATE)) {
            setConsoleInputDisabled();
            invokeHelloDialogue();
        } else if (message.startsWith(ServerThread.CODE_HELLO_SUCCESS)) {
            System.out.println(message.substring(ServerThread.CODE_HELLO_SUCCESS.length()));
            setConsoleInputEnabled();
        } else {
            System.out.println(message);
        }
    }

    /**
     * Pause printing server massages.
     */
    public void pausePrintServerMessages() {
        printServerMessages = false;
    }

    /**
     * Unpause printing server massages and print all the messages received since it is paused.
     */
    public void unpausePrintServerMessages() {
        printServerMessages = true;
        if (!bufServerMessages.isEmpty()) {
            for (String bufServerMessage : bufServerMessages) {
                try {
                    processServerMessage(bufServerMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bufServerMessages.clear();
        }
    }

    /**
     * Pause console input in the main thread
     */
    public void setConsoleInputEnabled() {
        consoleInputEnabled = true;
    }

    /**
     * Unpause console input in the main thread
     */
    public void setConsoleInputDisabled() {
        consoleInputEnabled = false;
    }

    /**
     * Check procedure for the console input for the main thread
     */
    public boolean isConsoleInputEnabled() {
        return consoleInputEnabled;
    }

    /**
     * Send user's nickname to a server
     * @param userName nickname
     */
    public void sendHelloMessage(String userName) {
        sendMessage(ServerThread.CODE_HELLO + userName);
    }

    /**
     * Send to a server information that this client is no longer active and the server can free up appropriate nickname.
     */
    public void sendByeMessage() {
        sendMessage(ServerThread.CODE_BYE_TO_SERVER);
    }

    /**
     * Send a regular text message to all or specific users.
     * @param message text message
     */
    public void sendAMessage(String message) {
        sendMessage(ServerThread.CODE_BROADCAST_MESSAGE + message);
    }

    /**
     * Send a message to the server
     * @param message message
     */
    private void sendMessage(String message) {
        InetAddress address = null;
        try {
            address = InetAddress.getByName(serverHostname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        byte[] messageBytes = message.getBytes();
        DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, address, 4445);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void invokeHelloDialogue() throws IOException {
        BufferedReader brd = new BufferedReader(new InputStreamReader(System.in));
        pausePrintServerMessages();
        System.out.println("Please enter your name:");
        String userName = String.valueOf(brd.readLine());
        unpausePrintServerMessages();
        sendHelloMessage(userName);

    }


}
