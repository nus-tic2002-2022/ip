package tasks;

public class Task {
    private String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public boolean getDoneStatus(){
        return this.isDone;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public String getStatusCode(){
        if(isDone) {
            return "1";
        }
        return "0";
    }

    public String getSaveFormat(){
        return "|" + getStatusCode() + "|" + getDescription();
    }

}
