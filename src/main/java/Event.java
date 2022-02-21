
public class Event extends Task {

    protected String taskType = "E";
    protected String at;

    public Event(String description) {
        super(description.substring(0,description.lastIndexOf("/") -1).trim());
        this.at = description.substring(description.lastIndexOf("/") + 3, description.length()).trim();
    }

    @Override
    public void getTask () {
        System.out.println("[" + taskType + "] [" + getStatusIcon() + "] " + getDescription() + " (at: " + at + ")");
    }

    /*public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType() { return (taskType); }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() { return isDone; }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }*/


}
