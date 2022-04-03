package duke.task;

public class Todo extends Task {
    public Todo(Task task) {
        super(task.id, task.description, task.isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
