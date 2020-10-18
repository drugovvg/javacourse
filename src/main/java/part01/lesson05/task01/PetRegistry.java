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
    private final Set<Pet> animals = new TreeSet<>(new Comparator<Pet>() {
        @Override
        public int compare(Pet p1, Pet p2) {
            if (p1.getOwner().getName().trim().compareTo(p2.getOwner().getName().trim()) == 1) {
                return 1;
            } else if (p1.getOwner().getName().trim().compareTo(p2.getOwner().getName().trim()) == -1) {
                return -1;
            }

            if (p1.getName().trim().compareTo(p2.getName().trim()) == 1) {
                return 1;
            } else if (p1.getName().trim().compareTo(p2.getName().trim()) == -1) {
                return -1;
            }

            if (p1.getWeight() > p2.getWeight()) {
                return 1;
            } else if (p1.getWeight() < p2.getWeight()) {
                return -1;
            }

            return 0;

        }
    });

    private NavigableMap<String, Pet> animalsNameMap = new TreeMap<>();
    private NavigableMap<Integer, Pet> animalsIdMap  = new TreeMap<>();




    /**
     * Adding a Pet to the registry
     * @param pet
     */
    public void add(Pet pet){
        try {
            if (!animals.add(pet)) throw new IllegalArgumentException();
            animalsIdMap.put(pet.getId(), pet);
            animalsNameMap.put(pet.getName(), pet);
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

        if(animalsNameMap.containsKey(name)) {
            return animalsNameMap.get(name);
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

        if (!animalsIdMap.containsKey(id)) {
            return false;
        }

        Pet oldPet = animalsIdMap.get(id);
        Pet newPet = new Pet(id, name, weight, owner);

        animals.remove(oldPet);
        animals.add(newPet);

        animalsIdMap.replace(id, newPet);
        animalsNameMap.replace(name, newPet);

        return true;

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
