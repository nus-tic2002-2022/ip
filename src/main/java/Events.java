import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task{
    protected LocalDate eventTime;

    public Events (String task, String eventTime) {
        super(task);
        this.eventTime = LocalDate.parse(eventTime);;
    }

    @Override
    public String toString(){
        return String.format("[E][%s] %s (at %s)", (done?"x":" "), this.task,this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String taskToSaveFile() {
        return String.format("E|%d|%s|%s", (done?1:0) ,this.task ,this.eventTime);
    };
}
