package task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Please, choose which exception do you want to throw\n");
        System.out.println("" +
                "(1): Option 1 - NullPointerException\n" +
                "(2): Option 2 - ArrayIndexOutOfBoundsException\n" +
                "(3): Option 3 - My cool exception\n" +
                "(4): Option 4 - Just Say \"Hello world\" \n" +
                "(5): Exit");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int option = Integer.parseInt(String.valueOf(br.readLine()));
        switch (option) {
            case 1:

                // throwing NullPointerException
                Object obj = null;
                obj.toString();
                break;
            case 2:

                // throwing ArrayIndexOutOfBoundsException
                int[] array = new int[5];
                int boom = array[6];
                break;
            case 3:
                throw new IllegalStateException("My cool exception");
            case 4:
                System.out.println("Hello world!\n");
                break;
            case 5:
                System.out.println("Bye!\n");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }

    }
}
