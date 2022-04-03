package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDate date;
    protected String time;

    public Events(Task task, LocalDate by, String at) {
        super(task.id, task.description, task.isDone);
        this.date = by;
        this.time = at;
    }

    /**
     * Use task object output and add parameters related to events object
     *
     * @return output display of event object
     */
    @Override
    public String toString() {
        String datetime = this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " " + this.time;
        return "[E]" + super.toString() + " (at: " + datetime + ")";
    }

    /**
     * Get the date time of events object in string
     *
     * @return output date time display of event object
     */
    public String getDateTime() {
        return String.format("%s,%s", this.date, this.time);
    }
}
