package zhixuan.duke.data.task;

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
        this.dueDate = DateParser.parseDate(dueDate);
    }

    /**
     * Override setter for toString method
     *
     * When saving LocalDateTime as toString, Java automatically adds character 'T'
     * between date and time. .replace to replace the 'T'
     *
     * @return display format for Event
     **/
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dueDate.toString().replace("T", " ") + ")";
    }

    /**
     * Format for Event when saving to file
     *
     * When saving LocalDateTime as toString, Java automatically adds character 'T'
     * between date and time. .replace to replace the 'T'
     *
     * @return saved task format for Event
     **/
    public String toFile() {
        return "EVENT | " + super.toFile() + " | " + dueDate.toString().replace("T", " ");
    }
}
