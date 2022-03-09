
public class Todo extends Task {

    protected String taskType = "T";

    public Todo(String description) {
        super(description.trim());
    }

    @Override
    public void getTask () {
        System.out.println("[" + taskType + "] [" + getStatusIcon() + "] " + getDescription());
    }

    public void printTask () {
        System.out.println("Nice! I've added this task: ");
        getTask ();
    }

}
