package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends TasksWithDate {
    //protected LocalDate eventTime;

    public Events (String task, String taskDate) {
        super(task,taskDate);
    }

    @Override
    public String toString(){
        return String.format("[E][%s] %s (at %s)", (done?"x":" "), this.task,this.taskDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String taskToSaveFile() {
        return String.format("E|%d|%s|%s", (done?1:0) ,this.task ,this.taskDate);
    };

    @Override
    public void rescheduleTask(String rescheduleDate) {
        this.taskDate = LocalDate.parse(rescheduleDate);
    };
}
