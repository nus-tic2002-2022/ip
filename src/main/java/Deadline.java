import java.time.*;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate date;

    public Deadline(String description, LocalDate by, String enter) {
        super(description, enter);
        this.date = by;
    }

    @Override
    public LocalDate getDate(){
        return this.date;
    }

    @Override
    public String printTask() {
        return "[D]" + "["+getStatusIcon()+"] "+getDescription() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM/dd/yyyy")) + ")";
    }
}