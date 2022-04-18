package zhixuan.duke.data.task;

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
        this.dueDate = DateParser.parseDate(dueDate);
    }

    /**
     * Override setter for toString method
     *
     * When saving LocalDateTime as toString, Java automatically adds character 'T'
     * between date and time. .replace to replace the 'T'
     *
     * @return display format for Deadline
     **/
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.toString().replace("T", " ") + ")";
    }

    /**
     * Format for Deadline when saving to file
     *
     * When saving LocalDateTime as toString, Java automatically adds character 'T'
     * between date and time. .replace to replace the 'T'
     *
     * @return saved task format for Deadline
     **/
    public String toFile() {
        return "DEADLINE | " + super.toFile() + " | " + dueDate.toString().replace("T", " ");
    }
}
