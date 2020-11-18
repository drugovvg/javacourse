package part1.lesson13.part01;

public class Main {

    public static void main(String args[]) {

        int multiplier = 600;
        for (int i = 1; i < 600; i++) {
            System.out.println("Round " + i + " Free Memory: " + Runtime.getRuntime().freeMemory());
            int[] myIntList = new int[multiplier];

            for (int j = i; j > 1; j--) {
                myIntList[j] = i;
            }
            multiplier = multiplier * 10;
        }

    }

}