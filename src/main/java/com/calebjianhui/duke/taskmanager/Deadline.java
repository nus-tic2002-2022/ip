package com.calebjianhui.duke.taskmanager;

import com.calebjianhui.duke.common.Pair;
import com.calebjianhui.duke.enums.TaskDateStructure;
import com.calebjianhui.duke.parser.DateParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    public static final String COMMAND_SEPARATOR = " /by ";
    public static final String TYPE_INDICATOR = "D";

    private String rawDate;
    private Pair<TaskDateStructure, LocalDateTime> dateStructure;

    public Deadline (boolean isDone, String description, String dueDate) {
        super(isDone, description);
        this.setDate(dueDate);
    }

    /**
     * Getter for description
     * **/
    public String getDescription() {
        return description + " (by: " + rawDate + ")";
    }

    /**
     * Getter for description (raw)
     * **/
    public String getRawDescription() {
        return description + COMMAND_SEPARATOR + rawDate + ")";
    }

    /**
     * Getter for type of task
     * **/
    public String getType() {
        return TYPE_INDICATOR;
    }

    /**
     * Get the current date structure
     * **/
    @Override
    public TaskDateStructure getDateStructure() {
        return dateStructure.getFirst();
    }

    /**
     * Set the date of the task
     * **/
    @Override
    public void setDate(String newDate) {
        this.rawDate = newDate;
        this.dateStructure = DateParser.parseDateTimeString(newDate);
    }
}