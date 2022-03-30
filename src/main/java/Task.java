public class Task {
    protected String description;
    protected boolean isDone;
    protected String priority;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        //Default set to Low
        this.priority = "LOW ";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getPriority() { return (priority); } //Set Priority as HIGH

    //Mark as Done
    public void markAsDone() {
        this.isDone = true;
    }

    //Mark as Not Done
    public void markAsNotDone() {
        this.isDone = false;
    }

    //Set Priority HIGH
    public void setPriorityHigh() { this.priority = "HIGH"; }

    //Set Priority LOW
    public void setPriorityLow() { this.priority = "LOW "; }

    //Parent toString()
    public String toString() {
        return ( "[" + this.getStatusIcon() + "]" + "[Priority: " + this.getPriority() + "] " + description);
    }

}
