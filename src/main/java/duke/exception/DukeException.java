package duke.exception;

public class DukeException extends Exception {

    public final static String UnknownCommand = "What you saying bruh? I don't get what you mean, mind typing in details:";
    public final static String invalidInputCommand = "Sorry, your command is missing of things.";
    public final static String invalidDateCommand = "Sorry, please follow the following Deadline [Description] /by [dd/mm/yyyy] HHHH";
    public DukeException(String message) {
        super(message);
    }

}
