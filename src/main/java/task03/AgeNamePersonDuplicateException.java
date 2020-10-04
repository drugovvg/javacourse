package task03;

/**
 * This is a custom Exception for learning purposes.
 */
public class AgeNamePersonDuplicateException extends Exception {

    public AgeNamePersonDuplicateException() {
        super("It looks like we have persons with the same name and age in the list.");
    }

    public void printExceptionMsg() {
        System.err.println(getMessage());
    }

}
