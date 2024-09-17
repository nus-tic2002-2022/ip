package duke.exception;

/**
 * This class will store information of error related to operations in Duke.
 */
public class DukeException extends Exception{
    private String error;

    /**
     * Constructor for DukeException
     *
     * @param error Specific error described in String format.
     */
    public DukeException (String error) {
        this.error = error;
    }

    /**
     * Return error variable in DukeException class.
     *
     * @return error variable in String format.
     */
    public String getError () {
        return this.error;
    }
}
