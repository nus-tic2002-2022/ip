package duke;

import duke.Task;

public class ToDo extends Task
{
    public ToDo(String description) {
        super(description);
    }

    /**
     * Convert the task to string
     *
     * @return string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}