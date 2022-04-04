package duke.task;

import java.time.LocalDate;

public abstract class TasksWithDate extends Task{
    protected LocalDate taskDate;

    public TasksWithDate (String task, String taskDate) {
        super(task);
        this.taskDate = LocalDate.parse(taskDate);
    }

    public abstract void rescheduleTask (String dateToReschedule);
}
