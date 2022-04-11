public class Task {
    protected boolean isDone;
    protected String description;

    public void isDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void unmark(){
        isDone = false;
    }
}
