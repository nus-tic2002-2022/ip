package duke.tasklist;

public class Task {
    protected String description;
    public boolean isDone;
    protected char type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public char getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatusIconS() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    @Override
    public String toString() {
        return "description: " + description;
    }
}