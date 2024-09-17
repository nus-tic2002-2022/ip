package duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is an extension of Task class with the addition of "by" variable and also contains methods to access and modify variable.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline
     *
     * @param description description of Task.
     * @param by deadline of Task in LocalDateTime format.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.description = description;
        this.by = by;
    }

    /**
     * Returns deadline of Task.
     *
     * @return deadline of Task in String format.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Update deadline of Task with duration provided.
     *
     * @param by deadline of Task to update as.
     */
    public void setBy(String by){
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String toString() {
        String output = super.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a");
        return output.substring(0,1) + 'D' + output.substring(2) + "\tby : " + formatter.format(by);
    }
}
