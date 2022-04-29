package zhixuan.duke.data.exceptions;

public class UnknownCommandException extends Exception {

    public static String ERROR = "You have entered an invalid command.";
    public static final String EMPTY = "Task list is empty.";

    public UnknownCommandException() {
        super();
    }

    public UnknownCommandException(String errorMessage) {
        super(errorMessage);
        ERROR = errorMessage;
    }

    @Override
    public String getMessage () {
        return ERROR;
    }

}
