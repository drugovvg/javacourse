package part1.lesson11.task01;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.*;
import java.util.*;

public class QuoteServerThread extends Thread {

    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean moreQuotes = true;
    protected HashMap<Integer, Client> clientsAddressMap = new HashMap<>();
    protected HashMap<String, Client> clientsNameMap = new HashMap<>();

    public final static String CODE_HELLO = "[10]";
    public final static String CODE_HELLO_RESPONSE_NAME_DUPLICATE = "[11]";
    public final static String CODE_HELLO_SUCCESS = "[12]";
    public final static String CODE_HELLO_INITIATE = "[13]";
    public final static String CODE_BROADCAST_MESSAGE = "[20]";
    public final static String CODE_UNICAST_MESSAGE = "[30]";

    public QuoteServerThread() throws IOException {
        this("QuoteServerThread");
    }

    public QuoteServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);
    }

    public void run() {


        while (moreQuotes) {
            try {
                byte[] buf = new byte[256];
//                System.out.println(".");
//                try {
//                    sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
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

                System.out.println(s);

                if (s.startsWith(CODE_HELLO) && !clientsAddressMap.containsKey(aClient.hashCode())) {
                    String userName = s.substring(CODE_HELLO.length());
                    if (clientsNameMap.containsKey(userName)) {
                        String responseMessage = CODE_HELLO_RESPONSE_NAME_DUPLICATE + userName + " already in use. Please choose another nickname.";
                        sendMessage(responseMessage, aClient.getAddress(), aClient.getPort());
                    } else {
                        Client value = new Client(
                                s.substring(CODE_HELLO.length()),
                                address,
                                port
                        );
                        clientsAddressMap.putIfAbsent(aClient.hashCode(), value);
                        clientsNameMap.putIfAbsent(userName, value);
                        String responseMessage = CODE_HELLO_SUCCESS + userName + ", welcome to the server.";
                        sendMessage(responseMessage, aClient.getAddress(), aClient.getPort());
                    }

                } else if (s.startsWith(CODE_BROADCAST_MESSAGE) && clientsAddressMap.containsKey(aClient.hashCode())) {

                    Client fromClient = clientsAddressMap.get(aClient.hashCode());
                    String message = s.substring(CODE_BROADCAST_MESSAGE.length());

                    String nameTo = StringUtils.substringBetween(message, "@", ",");
                    if (nameTo != null) {
                        if (clientsNameMap.containsKey(nameTo)){
                            Client toClient = clientsNameMap.get(nameTo);
                            sendMessage(fromClient.getName() + " (privately): " + message,
                                    toClient.getAddress(),
                                    toClient.getPort());
                        } else {
                            sendMessage( "There is no user with such name: " + nameTo,
                                    fromClient.getAddress(),
                                    fromClient.getPort());
                        }
                    } else  {
                        clientsAddressMap.forEach((k, v) -> {
                            if (k != aClient.hashCode()) {
                                String forecastMessage = fromClient.getName() + ": " + message;
                                sendMessage(forecastMessage, v.getAddress(), v.getPort());
                            }
                        });
                    }
                } else if (!clientsAddressMap.containsKey(aClient.hashCode())){
                    sendMessage(CODE_HELLO_INITIATE, aClient.getAddress(), aClient.getPort());
                }

            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }

    public void pause() {
        moreQuotes = false;
        System.out.println("Paused");
    }

    public void unpause() {
        moreQuotes = true;
        System.out.println("Unpaused");
    }

    protected void sendMessage(String responseMessage, InetAddress address, int port) {
        byte[] message = responseMessage.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(message, message.length, address, port);
        try {
            socket.send(responsePacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    protected String getNextQuote() {
        String returnValue = null;
        try {
            if ((returnValue = in.readLine()) == null) {
                in.close();
                moreQuotes = false;
                returnValue = "No more quotes. Goodbye.";
            }
        } catch (IOException e) {
            returnValue = "IOException occurred in server.";
        }
        return returnValue;
    }
}
