package duke.task;

/**
 * A type of task without deadline
 */
public class Todo extends Task {

    public Todo() {
        this("");
    }

    public Todo(String description) {
        super(description);
        this.taskType = TaskType.TODO;
    }

    /**
     * Return customised string to print for Todo task
     * @return customised string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}
