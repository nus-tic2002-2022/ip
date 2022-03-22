package duke.tasklist;

/**
 * This class stores description and status of a Task and contains methods to access and modify them.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task
     */
    public Task() {
        this.description = "";
        this.isDone = false;
    }

    /**
     * Returns description of Task.
     *
     * @return description of Task.
     */
    public String getTask(){
        return this.description;
    }

    /**
     * Change the task status to status provided.
     * Returns true if change can be done to task status,
     * and returns false if change cannot be done to task status.
     *
     * @param isDone status to be changed to.
     * @return if there is any change in status of Task.
     */
    public boolean setTaskStatus(boolean isDone) {
        if (this.isDone == isDone) {
            return false;
        }
        else {
            this.isDone = isDone;
            return true;
        }
    }

    /**
     * Returns status of Task.
     *
     * @return status of Task in boolean.
     */
    public boolean getTaskStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String type = "T";
        String status = "";
        if (this.getTaskStatus()) {
            status = "[Done]\t";
        }
        else {
            status = "[ ]\t\t";
        }

        return "[" + type + "]\t" + status + "" + this.description;
    }
}