import java.time.*;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDate date;

    public Event(String description, LocalDate at) {
        super (description);
        this.date = at;
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override

    public String printTask() {
        return "[E]" + "["+getStatusIcon()+"] "+getDescription() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM/dd/yyyy")) + ")";
    }

}
