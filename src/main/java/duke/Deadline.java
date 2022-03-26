/**
 * Creates a Deadline object. Deadline object is inherited from Task object.
 *
 */
package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String taskType = "D";
    protected LocalDateTime by;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public Deadline(String description) {
        super(description.substring(0, description.lastIndexOf("by") -1).trim());
        this.by = LocalDateTime.parse(description.substring(description.lastIndexOf("by") + 3, description.length()).trim(), formatter);
    }

    @Override
    public void getTask () {
        System.out.println("[" + taskType + "] [" + getStatusIcon() + "] " + getDescription() + " (by: " + by + ")");
    }

    public String toString () {
        return "[" + taskType + "] [" + getStatusIcon() + "] " + getDescription() + " (by: " + by + ")";
    }

    public LocalDateTime getTaskDate () { return by; }

}
