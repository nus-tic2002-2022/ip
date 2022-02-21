
public class Todo extends Task {

    protected String taskType = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public void getTask () {
        System.out.println("[" + taskType + "] [" + getStatusIcon() + "] " + getDescription());
    }

    /*public Todo(String description) {
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
    }*/


}
