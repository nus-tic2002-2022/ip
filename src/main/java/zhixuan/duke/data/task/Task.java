package zhixuan.duke.data.task;

import java.time.LocalDateTime;

/**
 * Generic Task class
 **/
public class Task {

    protected String description;
    protected boolean isDone;
    protected LocalDateTime dueDate;

    /**
     * Task constructor
     *
     * @param description Description of task
     * @param isDone Whether the task is done
     **/
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Task constructor
     *
     * @return format string for done status
     **/
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Getter for due date
     *
     * @return due date in LocalDateTime
     **/
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Setter for isDone = true
     *
     * @return true if isDone is set to true, false if isDone is already true
     **/
    public boolean markAsDone() {
        if (!isDone) {
            isDone = true;
            return true;
        }
        return false;
    }

    /**
     * Setter for isDone = false
     *
     * @return true if isDone is set to false, false if isDone is already false
     **/
    public boolean markAsUndone() {
        if (isDone) {
            isDone = false;
            return true;
        }
        return false;
    }

    /**
     * Override setter for toString method
     *
     * Calls getStatusIcon to display isDone status as a 'X' icon
     *
     * @return display format for Task
     **/
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Format for task when saving to file
     *
     * @return saved task format for Task
     **/
    public String toFile() {
        return isDone + " | " + description;
    }

}
