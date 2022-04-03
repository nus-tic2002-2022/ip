package duke.task;

public class Todo extends Task {
    public Todo(Task task) {
        super(task.id, task.description, task.isDone);
    }

    /**
     * Use task object output and add parameters related to todo object
     *
     * @return output display of todo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
