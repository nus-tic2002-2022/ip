package duke.task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(){
        isDone = true;
    }

    public String getStatusIcon(){

        return (isDone ? "x" : " ");
    }

    public String toString(){
        return "[" + getStatusIcon() + "]" + description;
    }

    public String fileListToString(){
        return  description;
    }
}
