package duke.data.entity;

/**
 * This is Task class to modify description with user input and return.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * @return description of Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return description status.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Set the description status to 'done'.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Constructor for Task with parameter
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Default Constructor for Task.
     */
    public Task() {
    }

    /**
     * @return status with 'X' if the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Function to markAsDone.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Function to markAsNotDone.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * @return status of Task in boolean and save.
     */
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.getDescription());
    }

    public String toSaveStr() {
        return (this.isDone() ? "1" : "0") + " , " + this.getDescription();
    }
}


