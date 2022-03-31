package duke.data.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Extension of Task class to modify Event.
 */
public class Event extends Task {

    protected Date at;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * @return status of Event with user input and save.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + sdf.format(at) + ")";
    }

    @Override
    public String toSaveStr() {
        return "E , " + super.toSaveStr() + " , " + sdf.format(at);
    }

}