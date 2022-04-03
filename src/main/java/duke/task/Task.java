package duke.task;

public class Task {
    public int id;
    protected String description;
    protected boolean isDone;

    public Task(Integer id, String description) {
        this.description = description;
        this.id = id; // will get last number in the future
        this.isDone = false;
    }

    public Task(Integer id, String description, boolean isDone) {
        this.description = description;
        this.id = id; // will get last number in the future
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + description.split(" /")[0]);
    }

    public String getDesc() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUnDone() {
        isDone = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toStorage() {
        return String.format("%s,%s,%s,%s%s", this.getStatus(), this.getInstanceOf(), this.getId(), this.getDesc(), this.getTimings());
    }

    public String getInstanceOf() {
        if (this.getClass() == Deadline.class) {
            return "d";
        } else if (this.getClass() == Events.class) {
            return "e";
        } else {
            return "t";
        }
    }

    public String getTimings() {
        if (this.getClass() == Deadline.class) {
            return ("," + ((Deadline) this).getDate());
        } else if (this.getClass() == Events.class) {
            return ("," + ((Events) this).getDateTime());
        } else {
            return "";
        }
    }
}