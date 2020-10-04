package task03;

import java.time.Instant;
import java.util.List;

/**
 * This is an interface for sort engines.
 */
public interface SortEngine {
    /**
     * Method for sorting Person objects.
     * @param personList list of Person objects to sort.
     * @return list of Person objects.
     */
    List<Person> sort(List<Person> personList);

    /**
     * Get last amount of time spent during the sorting process.
     * @return amount of nanoseconds.
     */
    public long getLastSortTimeElapsed();
}
