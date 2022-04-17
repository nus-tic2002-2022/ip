package subTask;

/**
 * This program is a child of the Task program. It helps add an additional classification to the Task program.
 */


public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}