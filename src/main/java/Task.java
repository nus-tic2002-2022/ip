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

    //Mark as done
    public void markAsDone() {
        this.isDone = true;
    }

    //Mark as not done
    public void markAsNotDone() {
        this.isDone = false;
    }

    //Adding tod0 task at parent class
    public String toString() {
        return ( "[" + this.getStatusIcon() + "] " + description);
    }

}
