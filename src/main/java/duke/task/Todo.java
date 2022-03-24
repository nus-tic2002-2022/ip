package duke.task;

public class Todo extends Task{

    /**Constructs a basic task from user input
     * Expects input like "todo read book".
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
        taskType = "T";
        System.out.println(this);
        newTask();
    }
    /**Constructs a basic task from imported from a task file
     * Expects input like "todo read book".
     * @param description The description of the task
     */
    public Todo(String description, boolean isDone){
        super(description, isDone);
        this.isDone = isDone;
        taskType = "T";
    }
    /**
     * Returns a string that is to be saved into the task file
     * @return [taskType][status] description
     */
    @Override
    public String addToFile() {
        return toString();
    }

    /**
     * Returns a string that is used for displaying to the user
     * @return [taskType][status] description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
