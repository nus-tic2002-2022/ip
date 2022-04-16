package duke.task;

public class Deadline extends Task {

    protected String by;

    /**
     * Constructs Deadline task.
     * Has a deadline from user input.
     * Expects input like "deadline read book /by 2022-02-22 2200".
     *
     * @param description The description of the task.
     * @param by The date and time which the task expires.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        taskType = "D";
        setDT(by);
        System.out.println(this);
        newTask();
    }

    /**
     * Constructs Deadline task.
     * Imported from a task file.
     *
     * @param description The description of the task.
     * @param by The date and time which the task expires.
     * @param isDone The status of the task.
     */
    public Deadline(String description, String by, boolean isDone){
        super(description, isDone);
        this.by = by;
        taskType = "D";
        setDT(by);
    }

    /**
     * Returns the task in the format to be added to the task file.
     *
     * @return [taskType][status] description (by: date).
     */
    @Override
    public String addToFile() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    /**
     * Return the task in the format used for displaying to the user.
     *
     * @return [taskType][status] description (by: date).
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + printDT() + ")";
    }
}