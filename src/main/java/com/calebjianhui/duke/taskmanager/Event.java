package com.calebjianhui.duke.taskmanager;

public class Event extends Task {
    public static final String COMMAND_SEPARATOR = " /at ";
    public static final String TYPE_INDICATOR = "E";

    private final String startTime;

    public Event (boolean isDone, String description, String startTime) {
        super(isDone, description);
        this.startTime = startTime;
    }

    /**
     * Getter for description
     * **/
    public String getDescription() {
        return description + " (at: " + startTime + ")";
    }

    /**
     * Getter for description (raw)
     * **/
    public String getRawDescription() {
        return description + COMMAND_SEPARATOR + startTime + ")";
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
        return startTime;
    }
}
