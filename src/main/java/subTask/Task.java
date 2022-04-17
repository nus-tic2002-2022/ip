package subTask;

/**
 * This Task program defines the basic structure for a Task class.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}