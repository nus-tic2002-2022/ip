package duke.task;

public class Todo extends Task{

    /**
     * Todo constructor.
     * Created from user input.
     * Expects input like "todo read book".
     *
     * @param description The description of the task
     */
    public Todo(String description) {
        super(description);
        taskType = "T";
        System.out.println(this);
        newTask();
    }
    /**
     * Todo contructor.
     * Created from imported from a task file.
     * Expects input like "todo read book".
     *
     * @param description The description of the task
     */
    public Todo(String description, boolean isDone){
        super(description, isDone);
        this.isDone = isDone;
        taskType = "T";
    }
    /**
     * addToFile string is appended to the task file.
     *
     * @return [taskType][status] description
     */
    @Override
    public String addToFile() {
        return toString();
    }

    /**
     * toString string used for displaying to the user.
     *
     * @return [taskType][status] description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
