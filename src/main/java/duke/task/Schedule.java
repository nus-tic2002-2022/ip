package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Schedule extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;

    public Schedule(String task, String startDate, String endDate) throws DateTimeParseException {
        super(task);
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Override
    public String toString(){
        String startDateString = this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endDateString =   this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return String.format("[S][%s] %s (from %s to %s)", (done?"x":" "), this.task,startDateString,endDateString);
    }

    @Override
    public String taskToSaveFile() {
        return String.format("S|%d|%s|%s|%s", (done?1:0) ,this.task ,this.startDate,this.endDate);
    };
}
