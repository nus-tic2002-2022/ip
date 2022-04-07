package duke.todo;

import duke.task.Task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        int markAsDone = super.isDone() ? 1 : 0;
        return "T|" + markAsDone + "|" + super.getDescription();
    }
}
