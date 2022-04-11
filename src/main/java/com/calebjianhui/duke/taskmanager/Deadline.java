package com.calebjianhui.duke.taskmanager;

import com.calebjianhui.duke.common.Pair;
import com.calebjianhui.duke.enums.TaskDateStructure;
import com.calebjianhui.duke.parser.DateParser;

import java.time.LocalDateTime;

public class Deadline extends Task implements DateModule{
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
     *
     * @return Description of task
     * **/
    public String getDescription() {
        return description + " (by: " + rawDate + ")";
    }

    /**
     * Gets the description
     *
     * @param isFormatted Should the result be formatted nicely
     * @return Description of task
     **/
    @Override
    public String getDescription(boolean isFormatted) {
        if (isFormatted) {
            return getDescription();
        } else {
            return description;
        }
    }

    /**
     * Gets the raw description (as given by user)
     * **/
    @Override
    public String getRawDescription() {
        return description + COMMAND_SEPARATOR + rawDate;
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
    public Pair<TaskDateStructure, LocalDateTime> getDateStructure() {
        return dateStructure;
    }

    /**
     * Set the date of the task
     * **/
    @Override
    public void setDate(String newDate) {
        this.rawDate = newDate;
        this.dateStructure = DateParser.parseDateTimeString(newDate);
    }

    /**
     * Get the date of the task
     * **/
    @Override
    public String getDate(boolean isFormatted) {
        if (isFormatted) {
            if (getDateStructure().getFirst().equals(TaskDateStructure.UNSTRUCTURED_DATE_STRING)) {
                return "NA";
            } else {
                return DateParser.convertToDateString(dateStructure.getSecond());
            }
        } else {
            return rawDate;
        }
    }
}