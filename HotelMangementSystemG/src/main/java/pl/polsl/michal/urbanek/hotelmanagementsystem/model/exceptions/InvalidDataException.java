package pl.polsl.michal.urbanek.hotelmanagementsystem.model.exceptions;

/**
 * Exception class representing all abnormal situations in models operations
 * 
 * @author Michał Urbanek
 * @version 1.0
 */
public class InvalidDataException extends Exception {

    /**
     * Constructor
     * @param message an exception message
     */
    public InvalidDataException(String message) {
        super(message);
    }
}
