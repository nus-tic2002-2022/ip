package exception;

public class InvalidDateException extends Exception{
    @Override
    public String getMessage() {
        return "Invalid date provided. Please provide in yyyy-mm-dd.";
    }
}
