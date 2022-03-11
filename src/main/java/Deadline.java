
public class Deadline extends Task {

    protected String taskType = "D";
    protected String by;
    protected int index;

    public Deadline(String description) {
        super(description.substring(0, description.lastIndexOf("by") -2).trim());
        this.by = description.substring(description.lastIndexOf("by") + 3, description.length()).trim();
    }

    @Override
    public void getTask () {
        System.out.println("[" + taskType + "] [" + getStatusIcon() + "] " + getDescription() + " (by: " + by + ")");
    }

    public String toString () {
        return "[" + taskType + "] [" + getStatusIcon() + "] " + getDescription() + " (by: " + by + ")";
    }

    public void printTask () {
        System.out.println("Nice! I've added this task: ");
        getTask ();
    }

}
