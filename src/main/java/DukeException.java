public class DukeException extends Exception {
    private final String mistake;

    public DukeException (String mistake) { this.mistake = mistake; }

    public String getError() {
        return this.mistake;
    }
}
