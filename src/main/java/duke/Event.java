/**
 * Creates an Event object. Event object is inherited from Task object.
 */
package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String taskType = "E";
    //protected String at;
    protected LocalDateTime at;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public Event(String description) {
        super(description.substring(0,description.lastIndexOf("at") -2).trim());
        //this.at = description.substring(description.lastIndexOf("at") + 3, description.length()).trim();
        this.at = LocalDateTime.parse(description.substring(description.lastIndexOf("at") + 3, description.length()).trim(), formatter);
    }

    @Override
    public void getTask () {
        System.out.println("[" + taskType + "] [" + getStatusIcon() + "] " + getDescription() + " (at: " + at + ")");
    }

    public String toString () {
        return "[" + taskType + "] [" + getStatusIcon() + "] " + getDescription() + " (at: " + at + ")";
    }

    public void printTask () {
        System.out.println("Nice! I've added this task: ");
        getTask ();
    }


}
