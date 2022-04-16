package duke.tasklist;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class event extends task{
    protected LocalDate by;
    public event(String description,String by , String time) {
        super(description);
        String [] words = by.split("/");
        String date = String.format("%02d", Integer.valueOf(words[0]));
        String month=words[1];
        String year=words[2];
        String fullDate=(year+"-"+month+"-"+date);
        this.by = LocalDate.parse(fullDate);
        type='E';

    }
    public void updateDate(LocalDate newDate) {
        this.by = newDate;
    }
    public LocalDate  getDate() {
        return this.by;
    }

    @Override
    public String toString() {
        return "["+getType()+"]" + getDescription() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
