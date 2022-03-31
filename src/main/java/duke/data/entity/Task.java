package duke.data.entity;
public class Task {
    private String description;
    private boolean isDone;


    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //Function to Mark as Done
    public void markAsDone() {
        this.isDone = true;
    }

    //Function to Mark as Not Done
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.getDescription());
    }
    public String toSaveStr() {
        return (this.isDone()?"1":"0") + " , " + this.getDescription();
    }
}


