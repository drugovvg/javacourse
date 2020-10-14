package part01.lesson05.task01;

import java.util.*;

public class PetRegistry {
    private Set animals = new TreeSet<>(new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            return s1.trim().compareTo(s2.trim());
        }
    });



    public void add(Pet pet){
        try {
            if (!animals.add(pet)) throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("Sorry, we are already have this Pet in the registry");
        }
    }

    public Pet searchByName(String name){

        List<Pet> animalsList = new ArrayList<Pet>(animals);

        int foundElementId = binarySearchByName(name, animalsList);
        if (foundElementId != -1) {
            return animalsList.get(foundElementId);
        }

        return null;
    }


    public boolean changePetsDataById(Integer id, String name, Float weight, Person owner) {

        List<Pet> animalsList = new ArrayList<Pet>(animals);

        int foundElementId = binarySearchById(id, animalsList);
        if (foundElementId != -1) {
            animalsList.set(foundElementId, new Pet(id, name, weight, owner));
            animals.clear();
            animals.addAll(animalsList);
            return true;
        }

        return false;
    }

    private int binarySearchById(Integer id, List<Pet> animalsList) {
        int firstIndex = 0;
        int lastIndex = animalsList.size() - 1;

        // условие прекращения (элемент не представлен)
        while(firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            // если средний элемент - целевой элемент, вернуть его индекс
            if (animalsList.get(middleIndex).getId().equals(id)) {
                return middleIndex;
            }

            // если средний элемент меньше
            // направляем наш индекс в middle+1, убирая первую часть из рассмотрения
            else if (animalsList.get(middleIndex).getId() < id)
                firstIndex = middleIndex + 1;

                // если средний элемент больше
                // направляем наш индекс в middle-1, убирая вторую часть из рассмотрения
            else if (animalsList.get(middleIndex).getId() > id)
                lastIndex = middleIndex - 1;

        }
        return -1;
    }

    private int binarySearchByName(String name, List<Pet> animalsList) {
        int firstIndex = 0;
        int lastIndex = animalsList.size() - 1;

        // условие прекращения (элемент не представлен)
        while(firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            // если средний элемент - целевой элемент, вернуть его индекс
            if (animalsList.get(middleIndex).getName().equals(name)) {
                return middleIndex;
            }

            // если средний элемент меньше
            // направляем наш индекс в middle+1, убирая первую часть из рассмотрения
            else if (animalsList.get(middleIndex).getName().compareTo(name) == 1)

                firstIndex = middleIndex + 1;

                // если средний элемент больше
                // направляем наш индекс в middle-1, убирая вторую часть из рассмотрения
            else if (animalsList.get(middleIndex).getName().compareTo(name) == -1)
                lastIndex = middleIndex - 1;

        }
        return -1;
    }


}
