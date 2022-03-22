package duke.commands;

import duke.tasklist.Task;

/**
 * This class will store and provide feedback based on executed actions.
 */
public class ResultCommand {
    public String feedback;
    public Task targetItem;
    public String targetString;

    /**
     * Constructor for ResultCommand
     *
     * @param feedback Feedback based on executed actions.
     */
    public ResultCommand(String feedback) {
        this.feedback = feedback;
        this.targetItem = null;
        this.targetString = null;
    }

    /**
     * Constructor for ResultCommand
     *
     * @param feedback Feedback based on executed actions.
     * @param task Task which the executed action is based on.
     */
    public ResultCommand(String feedback, Task task) {
        this.feedback = feedback;
        this.targetItem = task;
        this.targetString = null;
    }

    /**
     * Constructor for ResultCommand
     *
     * @param feedback Feedback based on executed actions.
     * @param message Message which complements the feedback variables.
     */
    public ResultCommand(String feedback, String message) {
        this.feedback = feedback;
        this.targetString = message;
        this.targetItem = null;
    }

    /**
     * Return feedback variable stored in ResultCommand class
     *
     * @return feedback in String format.
     */
    public String getFeedback() {
        return this.feedback;
    }

    /**
     * Return targetString variable stored in ResultCommand class
     *
     * @return targetString in String format.
     */
    public String getTargetString() {
        return this.targetString;
    }

    /**
     * Return targetItem variable stored in ResultCommand class
     *
     * @return targetItem as Task.
     */
    public Task getTargetItem() {
        return this.targetItem;
    }
}
