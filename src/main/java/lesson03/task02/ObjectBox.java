package lesson03.task02;

import java.util.List;

/**
 * A box for some objects
 */
public class ObjectBox {

    List aCollection;

    /**
     * Constructor
     * @param aCollection
     */
    public ObjectBox(List aCollection) {
        this.aCollection = aCollection;
    }

    /**
     * Getter for the aCollection
     */
    public List getaCollection() {
        return aCollection;
    }

    /**
     * Setter for the aCollection
     */
    public void setaCollection(List aCollection) {
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
