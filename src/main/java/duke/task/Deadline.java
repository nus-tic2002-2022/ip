package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(Task task, LocalDate by) {
        super(task.id, task.description, task.isDone);
        this.date = by;
    }

    /**
     * Use task object output and add parameters related to deadline object
     *
     * @return output display of deadline object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Get the dateline of current task
     *
     * @return deadline in string format
     */
    public String getDate() {
        return this.date.toString();
    }
}
