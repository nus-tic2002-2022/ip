
public class AllException extends Exception{

    final static String UnknownCommand="What you saying bruh? I don't get what you mean, mind typing in details:";
    final static String invalidInputCommand="Sorry, your command is missing of things.";

    public AllException(String message) {
        super(message);
    }
}
