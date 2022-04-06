/**
 * Creates a Task object. There are 3 subtypes - To do, Deadline, Event.
 */
package duke;

import java.time.LocalDateTime;

abstract class Task {

    protected String description;
    protected boolean isDone;
    protected LocalDateTime taskDate;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public Boolean getStatusBoolean() {
        return (isDone);
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

    public void getTask() {
        System.out.println("[" + getStatusIcon() + "] " + getDescription());
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public LocalDateTime getTaskDate() {
        return taskDate;
    }

}
