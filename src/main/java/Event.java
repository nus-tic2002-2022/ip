
public class Event extends Task {

    /*protected String description;
    protected boolean isDone;*/
    protected String taskType;
    protected int index = 0;

    public Event(String description) {
        super(description);
    }

    @Override
    public void getTask () {
        taskType = "E";
        System.out.println("[" + taskType + "] [" + getStatusIcon() + "] " + getDescription());
    }

    /*public Event(String description) {
        index = description.indexOf('/');
        this.description = description.substring(0, index-1) + "(at:" + description.substring(index+3,description.length()) + ")";
        this.isDone = false;
        this.taskType = "E";
    }*/

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
