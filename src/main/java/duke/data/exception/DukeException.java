package main.java.duke.data.exception;

/**
 * This class will store information of error related to operations in Duke.
 */

public class DukeException extends Exception {
    public DukeException(String error) {
        super(error);
    }
}
