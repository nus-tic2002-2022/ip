import java.io.IOException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void newTask(){
        try {
            TaskFile.appendTask(toString());
            System.out.println("The task was saved successfully");
        } catch (IOException i){
            System.out.println("☹ OOPS!!! The task was not saved to the file.");
        }
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    //...
}