package com.calebjianhui.duke.taskmanager;

public class Deadline extends Task {
    public static final String COMMAND_SEPARATOR = " /by ";
    public static final String TYPE_INDICATOR = "D";

    private final String dueDate;

    public Deadline (boolean isDone, String description, String dueDate) {
        super(isDone, description);
        this.dueDate = dueDate;
    }

    /**
     * Getter for description
     * **/
    public String getDescription() {
        return description + " (by: " + dueDate + ")";
    }

    /**
     * Getter for description (raw)
     * **/
    public String getRawDescription() {
        return description + COMMAND_SEPARATOR + dueDate + ")";
    }

    /**
     * Getter for type of task
     * **/
    public String getType() {
        return TYPE_INDICATOR;
    }

    /**
     * Getter for date of task
     * **/
    public String getDate() {
        return dueDate;
    }
}