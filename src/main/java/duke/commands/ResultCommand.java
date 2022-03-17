package duke.commands;

import duke.tasklist.Task;

public class ResultCommand {
    public String feedback;
    public Task targetItem;
    public String targetString;

    public ResultCommand(String feedback) {
        this.feedback = feedback;
        this.targetItem = null;
    }

    public ResultCommand(String feedback, Task task) {
        this.feedback = feedback;
        this.targetItem = task;
        this.targetString = null;
    }

    public ResultCommand(String feedback, String message) {
        this.feedback = feedback;
        this.targetString = message;
        this.targetItem = null;
    }

    public String getFeedback() {
        return this.feedback;
    }

    public String getTargetString() {
        return this.targetString;
    }

    public Task getTargetItem() {
        return this.targetItem;
    }
}
