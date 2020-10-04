package task03;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * This class contains some useful methods for list of Person objects
 */
public class PersonCollection {

    /**
     * This method generates List of Person objects with random parameters
     *
     * @param n amount of Persons to generate
     * @return  List of Person objects
     */
    public static List<Person> generate(int n){

        List<Person> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int age = new Random().nextInt(100);
            Sex sex = new Sex(Sex.RANDOMSEX);

            /**
             * Trying lambda functions in Java to generate something similar to a name with
             * first capitalized letter. The only parameter is the length of the random string.
             */
            Function<Integer, String> generatedName =
                    (length) -> {
                        String name = RandomStringUtils.randomAlphabetic(length).toLowerCase();
                        return name.substring(0, 1).toUpperCase() + name.substring(1);
                    };

            result.add(new Person(age, sex, generatedName.apply(5) + " " + generatedName.apply(10)));
        }

        return result;
    }
}
