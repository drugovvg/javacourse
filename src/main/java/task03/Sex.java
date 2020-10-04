package task03;

import java.util.Arrays;
import java.util.Random;

/**
 * This class represents a sex entity.
 */
public class Sex {
    private String sex;
    public static final String MAN = "Man";
    public static final String WOMAN = "Woman";
    public static final String RANDOMSEX = "RandomSex";

    public Sex(String sex) {
        if (!Arrays.asList(new String[]{MAN, WOMAN, RANDOMSEX}).contains(sex)) {
            throw new IllegalArgumentException("Value of the sex should by either " + MAN + " or " + WOMAN +
                    " or " + RANDOMSEX + ". Please use constants of the class " + this.getClass().getName());
        }
        if (sex.equals(RANDOMSEX)) {
            setSex((new Random().nextBoolean() ? MAN : WOMAN));
        } else {
            setSex(sex);
        }
    }

    @Override
    public String toString() {
        return getSex();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
