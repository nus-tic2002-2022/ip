package duke.task;

import java.util.List;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Display the status icon of this task (" " or "x" symbols) to the user.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    /**
     * Return a list of strings to user.
     *
     * @return This string task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "]" + description;
    }

    /**
     * Return a list of strings that can be saved.
     *
     * @return A task list for saving.
     */
    public List<String> getList() {
        return List.of(isDone ? "1" : "0", description);
    }

}
