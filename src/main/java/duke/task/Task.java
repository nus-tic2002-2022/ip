package duke.task;

import duke.exception.DukeException;

/**
 * Abstract class to derive customised tasks
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;
    String delimiter = "\\|";

    public Task(String description) {
        this.description = description;
    }

    public boolean getDoneStatus() { return this.isDone; }

    public TaskType getTaskType() { return this.taskType; }

    public String getDescription() { return this.description; }

    public void setDone() {isDone = true;}

    public void setUndone() {isDone = false;}

    /**
     * use icon to mark done status
     * @return icon
     */
    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    /**
     * Populate tasks details
     * @param s data from file in String
     * @throws DukeException for showing IO exception message
     */
    public void populateTaskDetails(String s) throws DukeException {
        String[] sSplit = s.split(delimiter);
        String taskStatus = sSplit[1].trim();
        String taskDescription = sSplit[2].trim();

        try {
            int i = Integer.parseInt(taskStatus);
            assert i == 0 || i == 1 : "Task status should be 0 or 1";
            this.isDone = (i == 1);
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.INVALID_TASK_STATUS_FILE + "[" + taskStatus + "] /!\\");
        }
        this.description = taskDescription;
    }

    /**
     * Return customised string to print for task
     * @return customised string
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
