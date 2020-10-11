package lesson03.task01;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        Number[] testArray = new Number[]{1.0, 1.0, 2.0, 3.0, 4.0};
//        Number[] testArray = new Number[]{1, 1, 2, 3, 4};
//        Integer[] testArray = new Integer[]{1, 1, 2, 3, 4};
        Short[] testArray = new Short[]{1, 1, 2, 3, 4};
        MathBox mathBox = new MathBox(testArray);

        System.out.println("Original:");
        for (Object item :
                mathBox.aSet) {
            System.out.println(item);
        }
        System.out.println("Sum:" + mathBox.summator());

        System.out.println("After removing 3:");
        mathBox.remove(3);
        for (Object item :
                mathBox.aSet) {
            System.out.println(item);
        }
        System.out.println("Sum:" + mathBox.summator());


        System.out.println("Splitter:");
        mathBox.splitter(2);

        for (Object item :
                mathBox.aSet) {
            System.out.println(item);
        }

        System.out.println("To String: " + mathBox.toString());
        System.out.println("Hash code: " + mathBox.hashCode());
        System.out.println("equals: " + mathBox.equals(mathBox));

    }

}
