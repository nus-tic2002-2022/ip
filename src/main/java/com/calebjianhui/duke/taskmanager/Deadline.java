package com.calebjianhui.duke.taskmanager;

public class Deadline extends Task {
    private String dueDate;

    public Deadline (String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Getter for description
     * **/
    public String getDescription() {
        return description + " (by: " + dueDate + ")";
    }

    /**
     * Getter for type of task
     * **/
    public String getType() {
        return "D";
    }
}