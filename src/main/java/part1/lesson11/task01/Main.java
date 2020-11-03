package part1.lesson11.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {
    public static void main(String[] args) throws IOException {

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
            case 1:
                QuoteServerThread quoteServerThread = new QuoteServerThread();
                quoteServerThread.start();

                System.out.println("Server is started. Server address:");
//                System.out.println(quoteServerThread.isAlive());

                DatagramSocket socket = new DatagramSocket();
                socket.connect(InetAddress.getByName("8.8.8.8"), 4445);
                System.out.println(socket.getLocalAddress().getHostAddress() + ":4445");

//                while (true){
//                    System.out.println("-");
//                    if (String.valueOf(br.readLine()) != null) {
//                        quoteServerThread.pause();
//                        String input = String.valueOf(br.readLine());
//                        if(input != null) {
//                            System.out.println("Input:" + input);
//                            quoteServerThread.unpause();
//                        }
//                    }
//                }
//
                break;
            case 2:
                System.out.println("Please enter a hostname of your chat server:");
                String hostname = String.valueOf(br.readLine());

                if (hostname.length() <= 0) hostname = "localhost";

                ClientThread clientThread = new ClientThread(hostname);
                clientThread.start();


//
//                clientThread.invokeHelloDialogue();
//
                System.out.println("Press enter to send a new message.");
                boolean consoleInputFlag = true;
                while (consoleInputFlag){
//                    System.out.println(".");
                    // TODO add this to another thread and add 1sec delay
                    if(clientThread.isConsoleInputEnabled()) {
                        // System.out.println("-");
                        if (String.valueOf(br.readLine()) != null) {
                            clientThread.pausePrintServerMessages();
                            System.out.println("You can use '@name:' construct to send direct messages. Message:");
                            String input = String.valueOf(br.readLine());
                            if (input.equals("quit")) {
                                System.out.println("Bye!");
                                // TODO send a command to the server
                                consoleInputFlag = false;
                            } else if (input != null) {

                                clientThread.sendAMessage(input);
                                // System.out.println("Input:" + input);
                                clientThread.unpausePrintServerMessages();
                                System.out.println("Message is sent. Press enter to send a new message.");
                            }
                        }
                    }


                }

                break;
            case 5:
                System.out.println("Bye!\n");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }


    }
}
