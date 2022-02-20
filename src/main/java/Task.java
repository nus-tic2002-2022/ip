public class Task {
    protected String description;
    protected boolean isDone;
    //protected String status;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        //this.status = "";
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

//    public void setStatus(String status) {
//        this.status = status;
//    }

    public String getStatusIcon() {
        return (isDone? "X" : " "); //mark done task with X
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
