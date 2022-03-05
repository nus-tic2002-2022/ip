package model;

public class Task {
    protected String description;
    protected boolean done;

    
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public boolean isDone(){
        return done;
    }

    public void setDone(boolean done){
        this.done = done;
    }

    public void printTask(){
        String status = " ";
        if(done){
            status = "X";
        }
        System.out.println("[" + status + "] " + description);
    }
}
