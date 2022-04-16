package tasks;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Task type: Deadline.
 */

public class Deadline extends Task{
    protected LocalDate date;
    protected int time;

    public Deadline(String description, LocalDate by, int time) {
        super(description);
        this.date = by;
        this.time = time;
    }

    @Override
    public LocalDate getDate(){
        return this.date;
    }

    @Override
    public String toString() {
        return " [D]" + "["+getStatusIcon()+"] "+getDescription() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) +" " + time + ")";
    }
}