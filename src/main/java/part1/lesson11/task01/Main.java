package part1.lesson11.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * This program implements a simple text chat. Firstly you should start one instance of the
 * program in the server mode. Then you can start other instances in the Client mode.
 * Clients can send broadcast messages and direct messages.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("" +
                "This is a simple chat application.\n" +
                "Running mode:\n" +
                "(1): Server\n" +
                "(2): Client\n" +
                "(3): Exit");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int option = 0;
        option = Integer.parseInt(String.valueOf(br.readLine()));

        switch (option) {
            case 1: // Server mode
                ServerThread serverThread = new ServerThread();
                serverThread.start();

                System.out.println("Server is started. Server address:");

                DatagramSocket socket = new DatagramSocket();
                socket.connect(InetAddress.getByName("8.8.8.8"), 4445);
                System.out.println(socket.getLocalAddress().getHostAddress() + ":4445");

                break;
            case 2: // Client mode
                System.out.println("Please enter a hostname of your chat server. Press enter to use 'localhost'.");
                String hostname = String.valueOf(br.readLine());

                if (hostname.length() <= 0) {
                    hostname = "localhost";
                }

                ClientThread clientThread = new ClientThread(hostname);
                clientThread.start();

                boolean consoleInputFlag = true; // We need to stop main thread sometimes
                while (consoleInputFlag) {
                    Thread.sleep(500);

                    /*
                    We have console input in the client thread
                    so we need to disable the console input in the main thread
                    to prevent conflicts.
                     */
                    if (clientThread.isConsoleInputEnabled()) {
                        if (String.valueOf(br.readLine()) != null) {
                            clientThread.pausePrintServerMessages();
                            System.out.println("You can use '@name,' syntax to send direct messages. Message:");
                            String input = String.valueOf(br.readLine());

                            /*
                            Quit command implementation. It sends a command to the server, so
                            the server frees up a name of the user on the server.
                             */
                            if (input.equals("quit")) {
                                clientThread.sendByeMessage();
                                System.out.println("Bye!");
                                consoleInputFlag = false;
                            } else if (input != null) {
                                clientThread.sendAMessage(input);
                                System.out.println("Message is sent. Press enter to send a new message.");
                                clientThread.unpausePrintServerMessages();
                            }
                        }
                    }

                }

                break;
            case 3:
                System.out.println("Bye!\n");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }


    }
}
