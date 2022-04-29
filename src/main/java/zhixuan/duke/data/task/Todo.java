package zhixuan.duke.data.task;

import zhixuan.duke.common.EnumTask;

/**
 * Todo task
 **/
public class Todo extends Task {

    /**
     * Todo constructor
     *
     * @param description Description of task
     * @param isDone Whether the task is done
     **/
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Override setter for toString method
     *
     * @return display format for Todo
     **/
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Format for todo when saving to file
     *
     * @return saved task format for Todo
     **/
    public String toFile() {
        return EnumTask.TODO + " | " + super.toFile();
    }
}
