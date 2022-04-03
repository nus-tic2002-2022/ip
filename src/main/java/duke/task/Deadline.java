package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(Task task, LocalDate by) {
        super(task.id, task.description, task.isDone);
        this.date = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    public String getDate() {
        return this.date.toString();
    }
}
