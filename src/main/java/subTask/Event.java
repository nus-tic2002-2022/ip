package subTask;

/**
 * This program is a child of the Task program. It helps add an additional classification to the Task program.
 */


public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}