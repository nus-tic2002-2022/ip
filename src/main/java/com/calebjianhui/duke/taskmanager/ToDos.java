package com.calebjianhui.duke.taskmanager;

public class ToDos extends Task {
    public static final String TYPE_INDICATOR = "T";

    public ToDos (boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Getter for description
     * **/
    public String getDescription() {
        return description;
    }

    /**
     * Getter for type of task
     * **/
    public String getType() {
        return TYPE_INDICATOR;
    }
}
