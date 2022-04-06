package tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

/** A EventTask object that stores a task with an event. */
public class Event extends Task {

    protected Date at;

    /** Constructor for the Event Class */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /** Updates the date of the event */
    @Override
    public void updateDate(Date newDate) {
        this.at = newDate;
    }

    /** Converts the task to a string */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateToString(at) + ")";
    }

    /** Converts the task to a savable format */
    @Override
    public String getSaveFormat() {
        return "E" + super.getSaveFormat() + "|" + at;
    }

    /** Converts the date of the task to a formatted string */
    public String dateToString(Date date) {
        return new SimpleDateFormat("MMM dd Ka").format(date);
    }
}
