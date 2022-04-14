package duke.task;

import duke.exception.DukeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A type of task with specific datetime
 */
public class Event extends Task {
    private LocalDateTime at;

    public Event() { this("");
    }

    public Event(String description) {
        super(description);
        this.at = LocalDateTime.now();
        this.taskType = TaskType.EVENT;
    }

    public Event(String description, LocalDateTime at) {
        super (description);
        this.at = at;
        this.taskType = TaskType.EVENT;
    }

    /**
     * Retrieve task datetime
     * @return date in YYYY-MM-DD HH:mm format
     */
    public LocalDateTime getAt() {
        return at;
    }

    /**
     * Populate Event task details
     * @param s data from file in String
     * @throws DukeException for showing IO exception message
     */
    @Override
    public void populateTaskDetails(String s) throws DukeException {
        super.populateTaskDetails(s);
        String[] sSplit = s.split(delimiter);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.at = LocalDateTime.parse(sSplit[3].trim(), format);
        } catch (Exception e) {
            throw new DukeException(DukeException.INVALID_DATETIME_FORMAT_FILE);
        }
    }

    /**
     * Return customised string to print for Event task
     * @return customised string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mm a"))  + ")";
    }

}
