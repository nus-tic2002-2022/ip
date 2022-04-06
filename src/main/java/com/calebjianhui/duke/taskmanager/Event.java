package com.calebjianhui.duke.taskmanager;

import com.calebjianhui.duke.common.Pair;
import com.calebjianhui.duke.enums.TaskDateStructure;
import com.calebjianhui.duke.parser.DateParser;

import java.time.LocalDateTime;

public class Event extends Task {
    public static final String COMMAND_SEPARATOR = " /at ";
    public static final String TYPE_INDICATOR = "E";

    private final String rawDate;
    private final Pair<TaskDateStructure, LocalDateTime> dateStructure;

    public Event (boolean isDone, String description, String startTime) {
        super(isDone, description);
        this.rawDate = startTime;
        this.dateStructure = DateParser.parseDateTimeString(startTime);
    }

    /**
     * Getter for description
     * **/
    public String getDescription() {
        return description + " (at: " + rawDate + ")";
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

}
