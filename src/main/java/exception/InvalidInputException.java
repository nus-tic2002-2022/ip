package exception;

public class InvalidInputException extends Exception{
    @Override
    public String getMessage() {
        return "What are you trying to here?? Please review your input.";
    }
    
}
