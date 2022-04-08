package duke.data.entity;

/**
 * This is Task class to modify description with user input and return.
 */
public class Task {
    private String description;
    private boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return description of Task.
     */
    public String getDescription() {
        return this.description;
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
        return (this.isDone()) ? "X" : " ";
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
     * @return task in string form
     * e.g.[ ] jlpt exam
     */
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.getDescription() + " ");
    }

    /**
     * @return task in string form with comman format
     * e.g. 0 , jlpt exam
     */
    public String toSaveStr() {
        return (this.isDone() ? "1" : "0") + " , " + this.getDescription();
    }
}


