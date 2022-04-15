
public class MissDescException extends Exception {

    public MissDescException(String msg) {
        System.out.println(" ☹ OOPS!!! The description of a " + msg + " cannot be empty.");
    }
}
