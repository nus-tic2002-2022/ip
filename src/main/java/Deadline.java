import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Task type: Deadline.
 */

public class Deadline extends Task{
    protected LocalDate date;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.date = by;
    }

    @Override
    public LocalDate getDate(){
        return this.date;
    }

    @Override
    public String toString() {
        return "[D]" + "["+getStatusIcon()+"] "+getDescription() + " (by: " + date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + ")";
    }
}