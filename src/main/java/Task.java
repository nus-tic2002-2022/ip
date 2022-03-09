
public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
    }

    public void markAsNotDone() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet: ");
    }

    public void getTask () {
        System.out.println("[" + getStatusIcon() + "] " + getDescription());
    }

    public String toString () {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public void printTask () {
        System.out.println("Nice! I've added this task: ");
        getTask ();
    }

}
