package part1.lesson11.task01;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ClientThread extends Thread {

    protected DatagramSocket socket = null;
    protected String serverHostname = "localhost";
    protected boolean printServerMessages = true;
    protected boolean consoleInputEnabled = false;
    protected List<String> bufServerMessages = new ArrayList<>();
    protected List<String> bufToServerMessages = new ArrayList<>();

    public ClientThread(String hostname) throws IOException {
        this("ClientThread", hostname);
    }

    public ClientThread(String name, String hostname) throws IOException {
        super(name);
        socket = new DatagramSocket();
        serverHostname = hostname;
    }

    public void run() {
        while (true) {
            byte[] buf = new byte[256];
            InetAddress address = null;
            try {
                address = InetAddress.getByName(serverHostname);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);

            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // get response
            packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // display response
            String received = new String(packet.getData(), 0, packet.getLength());

            System.out.println("Original:" + received);

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

    public void processServerMessage(String message) throws IOException {
        if (message.startsWith(QuoteServerThread.CODE_HELLO_RESPONSE_NAME_DUPLICATE)) {
            System.out.println(message.substring(QuoteServerThread.CODE_HELLO_RESPONSE_NAME_DUPLICATE.length()));
            invokeHelloDialogue();
        } else if (message.startsWith(QuoteServerThread.CODE_HELLO_INITIATE)) {
            setConsoleInputDisabled();
            invokeHelloDialogue();
        } else if (message.startsWith(QuoteServerThread.CODE_HELLO_SUCCESS)) {
            System.out.println(message.substring(QuoteServerThread.CODE_HELLO_SUCCESS.length()));
            setConsoleInputEnabled();
        } else {
            System.out.println(message);
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            if (String.valueOf(br.readLine()) != null) {
//                pausePrintServerMessages();
//                System.out.println("You can use '@name,' construct to send direct messages. Message:");
//                String input = String.valueOf(br.readLine());
////                if(input == "quit") {
////                    System.out.println("Bye!\n");
////                    break;
////                } else
//                if(input != null) {
//
//                    sendAMessage(input);
//                    // System.out.println("Input:" + input);
//                    unpausePrintServerMessages();
//                    System.out.println("Message is sent. Press enter to send a new message.");
//                }
//            }

        }
    }

    public void pausePrintServerMessages() {
        printServerMessages = false;
    }

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

    public void setConsoleInputEnabled() {
        consoleInputEnabled = true;
        System.out.println("setConsoleInputEnabled");
    }

    public void setConsoleInputDisabled() {
        consoleInputEnabled = false;
        System.out.println("setConsoleInputDisabled");
    }

    public boolean isConsoleInputEnabled() {
        System.out.println("isEndabled"+consoleInputEnabled);
        return consoleInputEnabled;
    }

    public void sendHelloMessage(String userName) {
        sendMessage(QuoteServerThread.CODE_HELLO + userName);
    }

    public void sendAMessage(String message) {
        sendMessage(QuoteServerThread.CODE_BROADCAST_MESSAGE + message);
    }

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
        sendHelloMessage(userName);
        unpausePrintServerMessages();

    }


}
