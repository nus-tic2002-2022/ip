/**
 * Creates a Task object. There are 3 subtypes - Todo, Deadline, Event.
 */
package duke;
public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public void getTask () {
        System.out.println("[" + getStatusIcon() + "] " + getDescription());
    }

    public String toString () {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public void printTask () {
        System.out.println("Nice! I've added this task: ");
        getTask ();
    }

}
