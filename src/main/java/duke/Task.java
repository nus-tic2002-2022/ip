package duke;
public class Task {
    boolean isDone;
    public String description;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return "[" + (isDone() ? ("X"):(" ")) + "] " + description;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public boolean isDone(){
        return this.isDone;
    }
}