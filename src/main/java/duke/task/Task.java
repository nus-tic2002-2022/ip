package duke.task;

import java.util.List;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }
    public Task(String isDone,String description){
        this.description=description;
        this.isDone= isDone.trim().equals("0") ? false:true;
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

    public List<String> getList() {
        return List.of(isDone ? "1" : "0", description);
    }
    public String toSavePattern(){return null;}
}
