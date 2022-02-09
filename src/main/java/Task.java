public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //Mark as Done
    public void markAsDone() {
        this.isDone = true;
    }

    //Mark as Not Done
    public void markAsNotDone() {
        this.isDone = false;
    }

}
