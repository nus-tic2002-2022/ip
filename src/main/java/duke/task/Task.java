package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public void setDone() {isDone = true;}
    public void setUndone() {isDone = false;}

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
