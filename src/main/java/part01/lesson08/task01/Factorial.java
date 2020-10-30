package part01.lesson08.task01;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentMap;

/**
 *
 */
class Factorial implements Runnable {
    private ConcurrentMap<Integer, BigInteger> map;
    private String name;
    private Integer intValue;
    Thread t;

    /**
     * This constructor immediately starts thread with intValue! calculations and puts it into the map
     * @param map ConcurrentHashMap where we will keep results and reuse them for intermediate calculations
     *            where intValue is a key, and intValue! is a value
     * @param threadName thread name
     * @param intValue integer
     */
    public Factorial(ConcurrentMap<Integer, BigInteger> map,
                     String threadName, Integer intValue) {
        this.map = map;
        this.name = threadName;
        this.intValue = intValue;
        t = new Thread(this, name);
        t.start();
    }

    public void run() {

        map.computeIfAbsent(intValue, k -> {
            BigInteger factorialValue = BigInteger.valueOf(1);
            for (int i = k; i > 1; i--) {
                BigInteger bigInteger = map.get(i);

                // Be smart - try to reuse results of the previous calculations:
                if (bigInteger == null) {
                    factorialValue = BigInteger.valueOf(i).multiply(factorialValue);
                } else {
                    factorialValue = bigInteger.multiply(factorialValue);
                    break;
                }
            }
            return factorialValue;
        });

    }
}
