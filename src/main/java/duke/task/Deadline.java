package duke.task;

public class Deadline extends Task {
    protected String date;

    public Deadline(Task task, String by) {
        super(task.id, task.description, task.isDone);
        this.date = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    public String getDate() {
        return this.date;
    }
}
