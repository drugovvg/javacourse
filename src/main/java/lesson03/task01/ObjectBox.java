package lesson03.task01;

import java.util.Collection;

/**
 * A box for some objects
 */
public class ObjectBox<T extends Collection> {

    T aCollection;

    /**
     * Constructor
     * @param aCollection
     */
    public ObjectBox(T aCollection) {
        this.aCollection = aCollection;
    }

    /**
     * Getter for the aCollection
     */
    public T getaCollection() {
        return aCollection;
    }

    /**
     * Setter for the aCollection
     */
    public void setaCollection(T aCollection) {
        this.aCollection = aCollection;
    }

    /**
     * Adding an object to the ObjectBox
     * @param item - an Object to add to the Box
     * @return boolean - true if it successfully added, false if it is not
     */
    public boolean addObject(Object item) {
        return getaCollection().add(item);
    }

    /**
     * Removing an Object from the ObjectBox in case it is exist
     * @param item - an Object to remove
     * @return boolean - true if it successfully removed, false if it is not
     */
    public boolean deleteObject(Object item) {
        return getaCollection().remove(item);
    }

    /**
     * Printing all the objects in ObjectBox
     */
    public void dump() {
        for (Object anObject:
                getaCollection()) {
            System.out.print(anObject.toString());
            System.out.print(" ");
        }
        System.out.print("\n");
    }


}
