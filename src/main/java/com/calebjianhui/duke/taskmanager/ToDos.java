package com.calebjianhui.duke.taskmanager;

/**
 * A ToDo task
 **/
public class ToDos extends Task {
    // For UI purposes to differentiate type of task
    public static final String TYPE_INDICATOR = "T";

    /**
     * Deadline constructor
     *
     * @param isDone Whether the task is done
     * @param description Description of task
     **/
    public ToDos (boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Getter for description
     *
     * @return Description of task
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Getter for type of task
     *
     * @return Type of task
     **/
    public String getType() {
        return TYPE_INDICATOR;
    }

}
