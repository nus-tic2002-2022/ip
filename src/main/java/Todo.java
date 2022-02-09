
public class Todo extends Task {

    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Todo(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "T";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType() { return (taskType); }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() { return isDone; }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }


}
