package lesson03.task01;

import java.util.*;

/**
 * MathBox is a class with implementation of some simple math methods on aSet HashSet
 */
public class MathBox {

    HashSet aSet;

    /**
     * Getter for the aSet
     * @return
     */
    public HashSet getaSet() {
        return aSet;
    }

    /**
     * Setter for the aSet
     * @return
     */
    public void setaSet(HashSet aSet) {
        this.aSet = aSet;
    }

    /**
     * MathBox constructor
     * @param anArray - initialize aSet
     * @param <T>
     */
    public <T extends Number> MathBox(T[] anArray) {
        setaSet(new HashSet(Arrays.asList(anArray)));
    }

    /**
     * This method purpose is to get sum of the all elements in the aSet.
     * @return sum of the all elements in the aSet
     */
    public double summator() {
        return getaSet().stream().mapToDouble(num -> Double.valueOf(num.toString())).sum();
    }

    /**
     * This method purpose is to divide all the elements in the aSet by the divider parameter and replace it with results of dividing.
     * @param divider
     * @param <T1>
     */
    public <T1 extends Number> void splitter(T1 divider) {
        double doubleDivider = Double.valueOf(divider.toString());
        double[] doubles = aSet.stream().mapToDouble(num -> {
            return Double.valueOf(num.toString()) / doubleDivider;
        }).toArray();

        aSet.clear();
        for (double aDouble : doubles) {
            aSet.add(aDouble);
        }

    }

    /**
     * Removing certain element in the aSet by value
     * @param value - value to searhc and remove
     */
    public void remove(Integer value) {

        Object firstASet = aSet.stream().findFirst().get();

        if(firstASet instanceof Double || firstASet instanceof Float) {
            aSet.remove(value.doubleValue());
        } else if (firstASet instanceof Short) {
            aSet.remove(value.shortValue());
        } else {
            aSet.remove(Integer.valueOf(value.toString()));
        }
    }

    /**
     * toString override
     * @return Resul of concatenation of all items in the aSet
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        for (Object s : aSet) {
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
