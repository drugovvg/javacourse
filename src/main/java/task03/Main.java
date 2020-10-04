package task03;

import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Main {

    public static void main(String[] args) throws IOException {

        /**
         * Read the parameters.
         */
        System.out.println("How many random people you would like us to generate:\n");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(String.valueOf(br.readLine()));

        /**
         * Let's generate lists.
         */
        List<Person> personList = PersonCollection.generate(n); // original list
        personList.set(1,personList.get(0)); // this string is to imitate persons with the same name and age (we have special exception for this case)

        StupidSortEngine stupidSortEngine= new StupidSortEngine(); // sort the list with the slow and simple sort engine
        List<Person> personList1 = stupidSortEngine.sort(personList);

        ComparatorSortEngine comparatorSortEngine = new ComparatorSortEngine(); // sort the list with the not hat slow engine
        List<Person> personList2 = comparatorSortEngine.sort(personList);

        /**
         * Print generated and sorted lists.
         */
        System.out.println("Original generated list:");
        for (Person person:
                personList) {
            System.out.println(person.getName() + " " + person.getAge() + " " + person.getSex());
        }
        System.out.println("-------------------");
        System.out.println("This list sorted with the Comparator engine:");
        for (Person person:
                personList2) {
            System.out.println(person.getName() + " " + person.getAge() + " " + person.getSex());
        }
        System.out.println("-------------------");
        System.out.println("This list sorted with the Stupid engine:");
        for (Person person:
                personList1) {
            System.out.println(person.getName() + " " + person.getAge() + " " + person.getSex());
        }
        System.out.println("-------------------");
        System.out.println("Elapsed time (nanoseconds):");
        System.out.println("Comparator engine - " + comparatorSortEngine.getLastSortTimeElapsed() +
                " Stupid engine - " + stupidSortEngine.getLastSortTimeElapsed());

        /**
         * This is the code for checking if we have persons with the same name and age in the list.
         *
         */
        Map<Pair<String, Integer>, List<Person>> listGroupedByNameAndAge =
                personList.stream().collect(Collectors.groupingBy(p -> Pair.of(p.getName(), p.getAge())));

        if(listGroupedByNameAndAge.size() != personList.size()){
            try {
                throw new AgeNamePersonDuplicateException();
            } catch (AgeNamePersonDuplicateException ex) {
                ex.printExceptionMsg();
            }
        }


    }

}
