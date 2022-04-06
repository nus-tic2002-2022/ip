package tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateToString(by) + ")";
    }

    @Override
    public String getSaveFormat() {
        return "D" + super.getSaveFormat() + "|" + by;
    }

    public String dateToString(Date date) {
        return new SimpleDateFormat("MMM dd").format(date);
    }
}
