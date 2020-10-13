package lesson03.task02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        /**
         * Initialize an objectBox with an empty ArrayList
         */
        List aCollection = new ArrayList<Object>();
        ObjectBox objectBox = new ObjectBox(aCollection);

        /**
         * Let's add some objects
         */
        objectBox.addObject(new Integer(1));
        objectBox.addObject(new Integer(2));
        objectBox.addObject(new Integer(3));

        /**
         * Let's print the content
         */
        objectBox.dump();

        /**
         * Let's delete an object and print the
         */
        objectBox.deleteObject(new Integer(2));
        objectBox.dump();

    }

}