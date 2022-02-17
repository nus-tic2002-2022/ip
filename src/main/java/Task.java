public class Task {
    protected String description;
    protected boolean isDone;

    Task(){}

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsNotDone(){
        this.isDone = false;
    }
}
