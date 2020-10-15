package part01.lesson05.task01;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        /*
          Initialization
         */
        Pet aPet1 = new Pet(1, "Bobik", (float) 10.2, new Person("John Silver", 31));
        Pet aPet2 = new Pet(2, "Strelka", (float) 12, new Person("Jack Sparrow", 40, Person.Sex.FEMALE));
        PetRegistry petRegistry = new PetRegistry();

        /*
          Adding pets
         */
        System.out.println("Pet Registry content:");
        petRegistry.add(aPet2);
        petRegistry.add(aPet1);
        petRegistry.dump();

        /*
          Check exception for duplicates
         */
        System.out.println("\nAdding duplicates:");
        petRegistry.add(aPet2);

        /*
          Search by name test
         */
        System.out.println("\nSearch by name results:");
        System.out.println(petRegistry.searchByName("Bobik"));
        System.out.println(petRegistry.searchByName("Bobik1"));
        System.out.println(petRegistry.searchByName("Strelka"));

        /*
          Change pet params by id
         */
        System.out.println("\nChange by id results:");
        petRegistry.changePetsDataById(1, "Bobik_changed", (float) 10.3, new Person("John Silver_changed", 13));
        petRegistry.dump();


    }

}
