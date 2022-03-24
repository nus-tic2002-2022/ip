package duke.task;

public class Deadline extends Task {

    protected String by;

    /**Constructs a task that has a deadline from user input
     * Expects input like "deadline read book /by 2022-02-22 2200".
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
     * Constructs a task that has a deadline imported from a task file
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
     * Returns a string that is to be saved into the task file
     * @return [taskType][status] description (by: date)
     */
    @Override
    public String addToFile() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    /**
     * Returns a string that is used for displaying to the user
     * @return [taskType][status] description (by: date)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + printDT() + ")";
    }
}