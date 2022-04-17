package duke;

import java.time.LocalDate;

public class Deadline extends Task
{
    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getBy() {
        return by;
    }

    /**
     * Convert the task to string
     *
     * @return string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}