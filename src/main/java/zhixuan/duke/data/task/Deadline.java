package zhixuan.duke.data.task;

import zhixuan.duke.common.EnumTask;
import zhixuan.duke.parser.DateParser;
import java.time.LocalDateTime;

/**
 * Deadline task
 **/
public class Deadline extends Task {

    protected LocalDateTime dueDate;

    /**
     * Deadline constructor
     *
     * @param description Description of task
     * @param isDone Whether the task is done
     * @param dueDate Due date of deadline
     **/
    public Deadline(String description, boolean isDone, String dueDate) {
        super(description, isDone);
        this.dueDate = DateParser.parseStringToDateTime(dueDate);
    }

    /**
     * Getter for due date
     *
     * @return due date in LocalDateTime
     **/
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
        return "[D]" + super.toString() + " (by: " + DateParser.parseDateToDisplayString(dueDate) + ")";
    }

    /**
     * Format for Deadline when saving to file
     *
     * Calls parseDateToString to parse dueDate into a different format
     *
     * @return saved task format for Deadline
     **/
    public String toFile() {
        return EnumTask.DEADLINE + " | " + super.toFile() + " | " + DateParser.parseDateToSaveString(dueDate);
    }
}
