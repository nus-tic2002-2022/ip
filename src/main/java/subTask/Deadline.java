package subTask;

/**
 * This program is a child of the Task program. It helps to add an additional classification to the Task program.
 * This form of program includes a task that includes both a description and its associated completion date.
 */


public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}