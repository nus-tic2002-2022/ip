package com.calebjianhui.duke.taskmanager;

import com.calebjianhui.duke.enums.TaskDateStructure;

public abstract class Task {
    protected boolean isDone;
    protected String description;

    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Getter for isDone
     * **/
    public boolean getDoneStatus() {
        return isDone;
    }

    /**
     * Getter for description
     * **/
    public abstract String getDescription();

    /**
     * Getter for type of task
     * **/
    public abstract String getType();

    /**
     * Setter for isDone
     *
     * @param isDone New Status
     * **/
    public void setDoneStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Setter for description
     *
     * @param description New description
     * **/
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the current date structure of the task
     * - Default return NO_DATE
     *
     * @return TaskDateStructure of Task
     * **/
    public TaskDateStructure getDateStructure() {
        return TaskDateStructure.NO_DATE;
    }

    /**
     * Sets the date for the task
     * - Default do nothing as this will be handled by the subclasses
     * **/
    public void setDate(String newDate) {}
}
