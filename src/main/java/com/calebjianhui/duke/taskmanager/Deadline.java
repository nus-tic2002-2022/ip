package com.calebjianhui.duke.taskmanager;

import com.calebjianhui.duke.common.Pair;
import com.calebjianhui.duke.enums.TaskDateStructure;
import com.calebjianhui.duke.parser.DateParser;

import java.time.LocalDateTime;

/**
 * A task that has a deadline
 **/
public class Deadline extends Task implements DateModule{
    // Command separator given by user (to separate description from date)
    public static final String COMMAND_SEPARATOR = " /by ";
    // For UI purposes to differentiate type of task
    public static final String TYPE_INDICATOR = "D";

    // Variables needed:
    private String rawDate;
    private Pair<TaskDateStructure, LocalDateTime> dateStructure;

    /**
     * Deadline constructor
     *
     * @param isDone Whether the task is done
     * @param description Description of task
     * @param dueDate Due date of task
     **/
    public Deadline (boolean isDone, String description, String dueDate) {
        super(isDone, description);
        this.setDate(dueDate);
    }

    /**
     * Getter for description
     *
     * @return Formatted Description of task
     **/
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
     *
     * @return Full description including date (as given by user)
     **/
    @Override
    public String getRawDescription() {
        return description + COMMAND_SEPARATOR + rawDate;
    }

    /**
     * Getter for type of task
     *
     * @return Type of task
     **/
    public String getType() {
        return TYPE_INDICATOR;
    }

    /**
     * Get the current date structure of the task
     *
     * @return Pair<TaskDateStructure, LocalDateTime> of Task
     **/
    @Override
    public Pair<TaskDateStructure, LocalDateTime> getDateStructure() {
        return dateStructure;
    }

    /**
     * Sets the date for the task
     *
     * @param newDate New date of the task
     **/
    @Override
    public void setDate(String newDate) {
        this.rawDate = newDate;
        this.dateStructure = DateParser.parseDateTimeString(newDate);
    }

    /**
     * Gets the date for the task
     *
     * @param isFormatted Whether to return the formatted date
     * @return Date of the task
     **/
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