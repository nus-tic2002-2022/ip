package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public abstract class TasksWithDate extends Task{
    protected LocalDate taskDate;

    public TasksWithDate (String task, String taskDate) throws DateTimeParseException {
        super(task);
        this.taskDate = LocalDate.parse(taskDate);
    }

    public abstract void rescheduleTask (String dateToReschedule);
}
