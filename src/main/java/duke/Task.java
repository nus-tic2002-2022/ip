package duke;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Set X based on isDone
     *
     * @return String X
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    /**
     * Get Description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return getStatusIcon + Description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}