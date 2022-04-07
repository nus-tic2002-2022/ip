package duke.event;

import duke.task.Task;

public class Event extends Task {
    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }

    @Override
    public String toFileString() {
        int markAsDone = super.isDone() ? 1 : 0;
        return "E|" + markAsDone + "|" + super.getDescription() + "|" + by;
    }
}
