package tasks;

import java.text.SimpleDateFormat;
import java.util.Date;
/** A DeadLineTask object that stores a task with a deadline. */
public class Deadline extends Task {

    protected Date by;

    /** Constructor for the Deadline Class */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /** Updates the date of the deadline */
    @Override
    public void updateDate(Date newDate) {
        this.by = newDate;
    }
    /** Converts the task to a string */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateToString(by) + ")";
    }

    /** Converts the task to a savable format */
    @Override
    public String getSaveFormat() {
        return "D" + super.getSaveFormat() + "|" + by;
    }

    /** Converts the date of the task to a formatted string */
    public String dateToString(Date date) {
        return new SimpleDateFormat("MMM dd").format(date);
    }

    public Date getDate() {
        return this.by;
    }
}
