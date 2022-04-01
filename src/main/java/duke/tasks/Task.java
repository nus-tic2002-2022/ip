package duke.tasks;
public class Task {
    protected boolean isDone;
    protected String description;

    //When task is created, it takes in the value of String description and stores it in the protected String description.
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    //The following method returns the description.
    public String getDescription() {
        return "[" + (isDone() ? ("X"):(" ")) + "] " + description;
    }

    //The following method sets the protected boolean isDone to the value specified.
    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    //The following method returns the value of the boolean isDone.
    public boolean isDone(){
        return this.isDone;
    }
}