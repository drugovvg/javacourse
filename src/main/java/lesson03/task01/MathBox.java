package lesson03.task01;

import java.util.*;
import java.util.LinkedList;

/**
 * MathBox is a class with implementation of some simple math methods
 */
public class MathBox extends ObjectBox{

    /**
     * MathBox constructor
     * @param anArray - initialize aSet
     * @param <T>
     */
    public <T extends Number> MathBox(T[] anArray) {
        super(new ArrayList<>(Arrays.asList(anArray)));
    }

    /**
     * This method purpose is to get sum of the all elements in the aSet.
     * @return sum of the all elements in the aSet
     */
    public double summator() {
        return getaCollection().stream().mapToDouble(num -> Double.valueOf(num.toString())).sum();
    }

    /**
     * This method purpose is to divide all the elements in the aSet by the divider parameter and replace it with results of dividing.
     * @param divider
     * @param <T1>
     */
    public <T1 extends Number> void splitter(T1 divider) {
        double doubleDivider = Double.valueOf(divider.toString());
        double[] doubles = getaCollection().stream().mapToDouble(num -> {
            return Double.valueOf(num.toString()) / doubleDivider;
        }).toArray();

        getaCollection().clear();
        for (double aDouble : doubles) {
            getaCollection().add(aDouble);
        }

    }

    /**
     * Adding an object to the ObjectBox
     * @param item - an Object to add to the Box
     * @return boolean - true if it successfully added, false if it is not
     */
    public boolean addObject(Object item) {
        try {
            if (item != null) throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("Putting objects into the MathBox through addObject is not working.");
            return false;
        }

        return true;
    }

    /**
     * Removing certain element in the aSet by value
     * @param value - value to search and remove
     * @return
     */
    public boolean remove(Object value) {

        return deleteObject(value);

    }

    /**
     * toString override
     * @return Result of concatenation of all items in the aSet
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        for (Object s : getaCollection()) {
            builder.append("," + s.toString());
        }
        builder.deleteCharAt(0);
        return builder.toString();

    }

    /**
     * Hash Code override based on toString() method
     * @return hash code
     */
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * Equals method override based on toString() method
     * @return boolean if an obj is equal to the MathBox
     */
    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}
