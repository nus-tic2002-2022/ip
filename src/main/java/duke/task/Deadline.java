package duke.task;

import duke.exception.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of task with deadline
 */
public class Deadline extends Task {
    private LocalDate by;

    public Deadline() {
        this("");
    }

    public Deadline(String description) {
        super(description);
        this.by = LocalDate.now();
        this.taskType = TaskType.DEADLINE;
    }

    public Deadline(String description, LocalDate by) {
        super (description);
        this.by = by;
        this.taskType = TaskType.DEADLINE;
    }

    /**
     * Retrieve task date
     * @return date in YYYY-MM-DD format
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Populate Deadline task details
     * @param s data from file in String
     * @throws DukeException for showing IO exception message
     */
    @Override
    public void populateTaskDetails(String s) throws DukeException {
        super.populateTaskDetails(s);
        String[] sSplit = s.split(delimiter);
        try {
            this.by = LocalDate.parse(sSplit[3].trim());
        } catch (Exception e) {
            throw new DukeException(DukeException.INVALID_DATE_FORMAT_FILE);
        }

    }

    /**
     * Return customised string to print for Deadline task
     * @return customised string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }

}
