package Task;

public abstract class Task {
    protected final String description;
    protected boolean isDone;
    protected String tag;
    protected boolean isTagged;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
        this.isTagged = false;
    }

    /**
     * This methods returns the status icon as a string, dependent on whether isDone is true or false.
     * "[X]" if it is true, "[ ]" if it is false.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * This method returns the description variable from of a Task object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * This method takes in a boolean and sets the isDone variable of the Task object using that
     * value.
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
        System.out.println("This task has been marked as " + (this.isDone ? "done:" : "not done:") + "\n" + "\t" + this.toString());
    }

    /**
     * This method takes in a String and sets the tag variable of the Task object.
     */
    public void setTag(String str) {
        this.tag = str;
        isTagged = !this.isTagged;
        System.out.println("This task has been " + (this.isTagged ? "tagged: " : "untagged: ") + "\n" + "\t" + this.toString());
    }

    public abstract String toString();


}