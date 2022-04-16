package duke.task;

public class Event extends Task {

    protected String at;

    /**
     * Constructs Event task.
     * Has a happening time from user input.
     * Expects input like "event read book /at 2022-02-22 2200".
     *
     * @param description The description of the task.
     * @param at The date and time which the task happens.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        taskType = "E";
        setDT(at);
        System.out.println(this);
        newTask();
    }
    /**
     * Constructs Event task.
     * Has a happening time imported from a task file.
     *
     * @param description The description of the task.
     * @param at The date and time which the task happens.
     * @param isDone The status of the task.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
        taskType = "E";
        setDT(at);
    }
    /**
     * Returns a string to be added to the task file.
     *
     * @return [taskType][status] description (at: date).
     */
    @Override
    public String addToFile() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }
    /**
     * Returns a string used for displaying to the user.
     *
     * @return [taskType][status] description (at: date).
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + printDT() +")";
    }
}