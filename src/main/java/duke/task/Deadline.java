package duke.task;

public class Deadline extends Task {

    protected String by;

    /**
     * Deadline task constructor.
     * That has a deadline from user input.
     * Expects input like "deadline read book /by 2022-02-22 2200".
     *
     * @param description The description of the task
     * @param by The date and time which the task expires
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
     * Deadline task constructor.
     * That has a deadline imported from a task file.
     *
     * @param description The description of the task
     * @param by The date and time which the task expires
     * @param isDone The status of the task
     */
    public Deadline(String description, String by, boolean isDone){
        super(description, isDone);
        this.by = by;
        taskType = "D";
        setDT(by);
    }

    /**
     * addToFile string is appended to the task file.
     *
     * @return [taskType][status] description (by: date)
     */
    @Override
    public String addToFile() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    /**
     * toString string used for displaying to the user.
     *
     * @return [taskType][status] description (by: date)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + printDT() + ")";
    }
}