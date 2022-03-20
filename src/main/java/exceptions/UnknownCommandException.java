package exceptions;

public class UnknownCommandException extends Exception {

    String error = "You have entered an invalid command.";

    public UnknownCommandException() {
        super();
    }

    public UnknownCommandException(String errorMessage) {
        super(errorMessage);
        error = errorMessage;
    }

    @Override
    public String getMessage () {
        return error;
    }

}
