
public class MissDescException extends Exception {
    /**
     *  This exception is used to check any missing descriptions of task from message given from _input[1].
     */

    public MissDescException(String msg) {
        System.out.println(" ☹ OOPS!!! The description of a " + msg + " cannot be empty.");
    }
}
