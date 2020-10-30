package part01.lesson08.task01;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The task is to calculate factorials for random array of integers.
 */
public class Main {

    public static void main(String[] args) {

        /*
        Let's generate random an array
         */
        Random random = new Random();
        int[] randomArray = random.ints(1, 20)
                .limit(10)
                .toArray();

        /*
        Let's initialize a ConcurrentHashMap so we can keep results and reuse them for intermediate calculations
         */
        ConcurrentHashMap<Integer, BigInteger> map = new ConcurrentHashMap<>();

        /*
        Starting threads
         */
        List<Factorial> factorialList = new ArrayList<>();

        for (int i : randomArray) {
            factorialList.add(new Factorial(map, "FactorialThread" + i, i));
        }

        /*
        Harvesting...
         */
        try {
            for (Factorial factorial : factorialList) {
                factorial.t.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread is interrupted");
        }

        /*
        Printing results
         */
        System.out.println(map);

    }

}
