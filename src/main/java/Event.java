
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

    public String toString () {
        return "[" + taskType + "] [" + getStatusIcon() + "] " + getDescription() + " (at: " + at + ")";
    }

    public void printTask () {
        System.out.println("Nice! I've added this task: ");
        getTask ();
    }


}
