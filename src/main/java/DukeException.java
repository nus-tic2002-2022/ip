public class DukeException extends Exception{
    private String error;

    public DukeException (String error) {
        this.error = error;
    }
    public String getError () {
        return this.error;
    }
}
