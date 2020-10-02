package lesson02.task02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.lang.Math;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Let's generate N random numbers then try to find square roots of the numbers and check if square of the floor function results equals to original numbers. I know it sounds boring, but please, enter N:\n");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(String.valueOf(br.readLine()));

        for (int i = 0; i < n; i++) {

            int k = new Random().nextInt(1000);

            double chanceForNegativeBase = new Random().nextDouble();
            k *= ((chanceForNegativeBase <= 0.01) ? -1 : 1); // add some chances for negative input

            if (k < 0) {
                throw new ArithmeticException("Oops, it looks like we just generated a negative input for the square root operation which " +
                        "does not make any sense in terms of mathematics: " + k);
            }

            double q = Math.sqrt(k);

            if (Math.pow(Math.floor(q), 2) == k) {
                System.out.println(k);
            }
        }

    }

}
