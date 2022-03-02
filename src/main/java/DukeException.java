public class DukeException extends Exception {
    private final String error;

    public DukeException (String error) {
        this.error = error;
    }

    public String getError() {
        return this.error;
    }
}
