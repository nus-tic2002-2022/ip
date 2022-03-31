public class Task {
    private String description;
    protected boolean isDone = false;
    private int id;

    private static int taskCount = 0; 

    public Task(String description) {
        this.description = description;
        taskCount++;
        this.id = taskCount;
    }
    
    public String getDescription() {
        return description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public int getId(){
        return id;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public boolean getDoneStatus(){
        return this.isDone;
    }

    public static int getNumberOfTask(){
        return taskCount;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + getDescription();
    }


}
