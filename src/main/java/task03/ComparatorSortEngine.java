package task03;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Simple sort engine based on using of Comparators.
 */
public class ComparatorSortEngine implements SortEngine{

    private long lastSortStartTime;
    private long lastSortFinishTime;

    @Override
    public List<Person> sort(List<Person> personList) {
        lastSortStartTime = System.nanoTime();

        List<Person> tempList = new ArrayList<>();
        tempList.addAll(personList);

        tempList.sort(PersonComparator);

        lastSortFinishTime = System.nanoTime();
        return tempList;
    }

    /**
     * Comparator for sorting the list.
     */
    public static final Comparator<Person> PersonComparator = new Comparator<Person>() {

        public int compare(Person p1, Person p2) {

            if (p1.getSex().toString() == Sex.MAN && p2.getSex().toString() == Sex.WOMAN) {
                return -1;
            } else if (p1.getSex().toString() == Sex.WOMAN && p2.getSex().toString() == Sex.MAN) {
                return 1;
            }

            if (p1.getAge() > p2.getAge()) {
                return -1;
            } else if (p1.getAge() < p2.getAge()) {
                return 1;
            }

            return p1.getName().compareToIgnoreCase(p2.getName());

        }
    };

    /**
     * Getting last duration of the sort() method in nanoseconds.
     * @return long
     */
    @Override
    public long getLastSortTimeElapsed(){
        return lastSortFinishTime - lastSortStartTime;
    }


}
