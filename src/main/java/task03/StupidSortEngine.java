package task03;

import java.util.ArrayList;
import java.util.List;

public class StupidSortEngine implements SortEngine {

    @Override
    public List<Person> sort(List<Person> personList) {

        /**
         * Sort everyone by Sex
         */
        List<Person> tempMenList = new ArrayList<>();
        List<Person> tempWomenList = new ArrayList<>();
        for (Person person :
                personList) {
            if (person.getSex().toString() == Sex.WOMAN) {
                tempWomenList.add(person);
            } else tempMenList.add(person);
        }

        /**
         * Then Sort everyone by Age
         */
        List<Person> tempList = new ArrayList<>();
        bubble_sort(tempMenList);
        bubble_sort(tempWomenList);
        tempList.addAll(tempMenList);
        tempList.addAll(tempWomenList);

        /**
         * Then Sort everyone Alphabetically
         */

        for (Person person :
                tempList) {
            System.out.println(person.getName() + " " + person.getAge() + " " + person.getSex());
        }

        int startCounter = -1;
        int endCounter;
        for (int i = 1; i < tempList.size(); i++) {
            if (tempList.get(i).getAge() == tempList.get(i - 1).getAge()) {
                if (startCounter == -1) {
                    startCounter = i - 1;
                }
            } else {
                if (startCounter > 0) {
                    endCounter = i - 1;
                    string_bubble_sort(tempList, startCounter, endCounter);
                    startCounter = -1;
                    endCounter = 0;
                }
            }
        }

        return tempList;
    }

    private static void bubble_sort(List<Person> input) {
        bubble_sort(input, false);
    }

    private static void bubble_sort(List<Person> input, boolean ascending) {

        int inputLength = input.size();
        Person temp;
        boolean is_sorted;

        for (int i = 0; i < inputLength; i++) {

            is_sorted = true;

            for (int j = 1; j < (inputLength - i); j++) {

                if (ascending) {
                    if (input.get(j - 1).getAge() > input.get(j).getAge()) {
                        temp = input.get(j - 1);
                        input.set(j - 1, input.get(j));
                        input.set(j, temp);
                        is_sorted = false;
                    }
                } else {
                    if (input.get(j - 1).getAge() < input.get(j).getAge()) {
                        temp = input.get(j - 1);
                        input.set(j - 1, input.get(j));
                        input.set(j, temp);
                        is_sorted = false;
                    }

                }

            }

            // is sorted? then break it, avoid useless loop.
            if (is_sorted) break;

        }

    }

    private static void string_bubble_sort(List<Person> input, int start, int end) {
        string_bubble_sort(input, start, end, false);
    }

    private static void string_bubble_sort(List<Person> input, int start, int end, boolean ascending) {

        int inputLength = end;
        Person temp;
        boolean is_sorted;

        for (int i = start; i < inputLength; i++) {
            is_sorted = true;

            for (int j = start + 1; j < (inputLength - i + start + 1); j++) {
                if (ascending) {
                    if (input.get(j - 1).getName().compareToIgnoreCase(input.get(j).getName()) < 0) {
                        temp = input.get(j - 1);
                        input.set(j - 1, input.get(j));
                        input.set(j, temp);
                        is_sorted = false;
                    }
                } else {
                    if (input.get(j - 1).getName().compareToIgnoreCase(input.get(j).getName()) > 0) {

                        temp = input.get(j - 1);
                        input.set(j - 1, input.get(j));
                        input.set(j, temp);
                        is_sorted = false;
                    }

                }

            }

            // is sorted? then break it, avoid useless loop.
            if (is_sorted) break;

        }

    }

}
