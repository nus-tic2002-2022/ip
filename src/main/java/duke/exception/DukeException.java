package duke.exception;

public class DukeException extends Exception {

    public final static String UnknownCommand = "What you saying bruh? I don't get what you mean, mind typing in details:";
    public final static String invalidInputCommand = "Sorry, your command is missing of things.";

    public DukeException(String message) {
        super(message);
    }

}
