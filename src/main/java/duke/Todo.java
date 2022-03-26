/**
 * Creates a Todo object. Todo object is inherited from Task object.
 * 
 */
package duke;
import java.time.LocalDateTime;

public class Todo extends Task {

    protected String taskType = "T";
    protected LocalDateTime taskDate;

    public Todo(String description) {
        super(description.trim());
        this.taskDate = LocalDateTime.now();
    }

    @Override
    public void getTask () {
        System.out.println("[" + taskType + "] [" + getStatusIcon() + "] " + getDescription());
    }

    public String toString () {
        return "[" + taskType + "] [" + getStatusIcon() + "] " + getDescription();
    }

    public LocalDateTime getTaskDate () { return taskDate; }

}
