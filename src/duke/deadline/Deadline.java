package duke.deadline;

import duke.task.Task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        int markAsDone = super.isDone() ? 1 : 0;
        return "D|" + markAsDone + "|" + super.getDescription() + "|" + by;
    }
}
