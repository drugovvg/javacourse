package part01.lesson05.task01;

import java.util.*;

/**
 * Pet registry entity
 */
public class PetRegistry {

    /**
     * We plan to have all the animals constantly sorted by owner's name, pet's name and pet's weight. So, we are using
     * TreeSet with a comparator.
     */
    private final Set<Pet> animals = new TreeSet<Pet>(new Comparator<Pet>() {
        @Override
        public int compare(Pet p1, Pet p2) {
            if(p1.getOwner().getName().trim().compareTo(p2.getOwner().getName().trim()) == 1){
                return 1;
            } else if (p1.getOwner().getName().trim().compareTo(p2.getOwner().getName().trim()) == -1) {
                return -1;
            }

            if(p1.getName().trim().compareTo(p2.getName().trim()) == 1){
                return 1;
            } else if (p1.getName().trim().compareTo(p2.getName().trim()) == -1) {
                return -1;
            }

            if(p1.getWeight() > p2.getWeight()){
                return 1;
            } else if (p1.getWeight() < p2.getWeight()) {
                return -1;
            }

            return 0;

        }
    });


    /**
     * Adding a Pet to the registry
     * @param pet
     */
    public void add(Pet pet){
        try {
            if (!animals.add(pet)) throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("Sorry, we already have this Pet in the registry: " + pet);
        }
    }

    /**
     * Search pet by pet's name
     * @param name - pet's name
     * @return first Pet entry with the name. null when nothing is found.
     */
    public Pet searchByName(String name){

        List<Pet> animalsList = new ArrayList<Pet>(animals);

        int foundElementId = binarySearchByName(name, animalsList);
        if (foundElementId != -1) {
            return animalsList.get(foundElementId);
        }

        return null;
    }

    /**
     * With this method you can find a pet by Id and replace it's parameters with the params.
     * @param id - pet's id to be found
     * @param name - new pet's name
     * @param weight - new pet's weight
     * @param owner - new owner
     * @return true if pet's id is found, false if pet's id is not found.
     */
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

    /**
     * Binary search by id
     * @param id - pet's id
     * @param animalsList - pet's list
     * @return index of the pet in the list, -1 if id is not found
     */
    private int binarySearchById(Integer id, List<Pet> animalsList) {
        int firstIndex = 0;
        int lastIndex = animalsList.size() - 1;


        while(firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;

            if (animalsList.get(middleIndex).getId().equals(id)) {
                return middleIndex;
            }

            else if (animalsList.get(middleIndex).getId() < id)
                firstIndex = middleIndex + 1;

            else if (animalsList.get(middleIndex).getId() > id)
                lastIndex = middleIndex - 1;

        }
        return -1;
    }

    /**
     * Binary search by name
     * @param name - pet's name
     * @param animalsList - pet's list
     * @return index of the pet in the list, -1 if name is not found
     */
    private int binarySearchByName(String name, List<Pet> animalsList) {
        int firstIndex = 0;
        int lastIndex = animalsList.size() - 1;

        while(firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;

            if (animalsList.get(middleIndex).getName().equals(name)) {
                return middleIndex;
            }

            else if (animalsList.get(middleIndex).getName().compareTo(name) < 0)

                firstIndex = middleIndex + 1;

            else if (animalsList.get(middleIndex).getName().compareTo(name) > 0)
                lastIndex = middleIndex - 1;

        }
        return -1;
    }

    /**
     * Print all the pet registry entries.
     * Do not forget it is already sorted by owner's name, pet's name and pet's weight because we are using
     * a TreeSet collection with a comparator as a storage for entries.
     */
    public void dump() {

        for (Pet aPet: animals) {
            System.out.println(aPet);
       }

    }


}
