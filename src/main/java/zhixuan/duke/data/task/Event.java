package zhixuan.duke.data.task;

import zhixuan.duke.common.EnumTask;
import zhixuan.duke.parser.DateParser;
import java.time.LocalDateTime;

/**
 * Event task
 **/
public class Event extends Task {

    protected LocalDateTime dueDate;

    /**
     * Event constructor
     *
     * @param description Description of task
     * @param isDone Whether the task is done
     * @param dueDate Due date of event
     **/
    public Event(String description, boolean isDone, String dueDate) {
        super(description, isDone);
        this.dueDate = DateParser.parseStringToDateTime(dueDate);
    }

    /**
     * Getter for due date
     *
     * @return due date in LocalDateTime
     **/
    @Override
    public LocalDateTime getDueDate() {
        return this.dueDate;
    }

    /**
     * Override setter for toString method
     * Calls parseDateToDisplayString to parse dueDate into a different format
     *
     * @return display format for Event
     **/
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateParser.parseDateToDisplayString(dueDate) + ")";
    }

    /**
     * Format for Event when saving to file
     *
     * Calls parseDateToString to parse dueDate into a different format
     *
     * @return saved task format for Event
     **/
    public String toFile() {
        return EnumTask.EVENT + " | " + super.toFile() + " | " + DateParser.parseDateToSaveString(dueDate);
    }
}
