package duke.task;

public class Events extends Task {
    protected String date;
    protected String time;

    public Events(Task task, String by, String at) {
        super(task.id, task.description, task.isDone);
        this.date = by;
        this.time = at;
    }

    @Override
    public String toString() {
        String datetime = this.date + " " + this.time;
        return "[E]" + super.toString() + " (at: " + datetime + ")";
    }

    public String getDateTime() {
        return String.format("%s,%s", this.date, this.time);
    }
}
