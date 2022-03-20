package exceptions;

public class InvalidTaskException extends Exception {

    String error;

    public InvalidTaskException(String errorMessage) {
        super(errorMessage);
        error = errorMessage;
    }

    @Override
    public String getMessage () {
        return error;
    }

}
