public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean markAsDone() {
        if (isDone == false) {
            isDone = true;
            return true;
        }
        return false;
    }

    public boolean markAsUndone() {
        if (isDone == true) {
            isDone = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
