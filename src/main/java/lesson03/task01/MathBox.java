package lesson03.task01;

import java.util.*;

public class MathBox {

    HashSet aSet;
    private Object Float;

    public <T extends Number> MathBox(T[] anArray) {
        aSet = new HashSet(Arrays.asList(anArray));
    }

    public double summator() {
        return aSet.stream().mapToDouble(num -> Double.valueOf(num.toString())).sum();
    }

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

    public void remove(Integer index) {

        Object firstASet = aSet.stream().findFirst().get();
//        aSet.remove(firstASet.getClass().cast(index));

        if(firstASet instanceof Double || firstASet instanceof Float) {
            aSet.remove(index.doubleValue());
        } else if (firstASet instanceof Short) {
            aSet.remove(index.shortValue());
        } else {
            aSet.remove(Integer.valueOf(index.toString()));
//            aSet.remove((Number)index);
        }
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        for (Object s : aSet) {
            builder.append("," + s.toString());
        }
        builder.deleteCharAt(0);
        return builder.toString();

    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}
