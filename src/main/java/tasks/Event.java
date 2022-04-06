package tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    protected Date at;

    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    @Override
    public void updateDate(Date newDate){
        this.at = newDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateToString(at) + ")";
    }

    @Override
    public String getSaveFormat() {
        return "E" + super.getSaveFormat() + "|" + at;
    }

    public String dateToString(Date date) {
        return new SimpleDateFormat("MMM dd Ka").format(date);
    }
}
