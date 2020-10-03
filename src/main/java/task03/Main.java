package task03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("How many random people you would like us to generate:\n");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(String.valueOf(br.readLine()));

        List<Person> personList = PersonCollection.generate(n);
        List<Person> personList1 = new StupidSortEngine().sort(personList);

        for (Person person:
                personList) {
            System.out.println(person.getName() + " " + person.getAge() + " " + person.getSex());
        }
        System.out.println("-------------------");
        for (Person person:
                personList1) {
            System.out.println(person.getName() + " " + person.getAge() + " " + person.getSex());
        }

    }

}
