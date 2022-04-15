public class Deadline extends Task {

    protected String by;

    /**
     * This method takes in 2 input parameters and create the 'deadline' task.
     * @param description The task description created
     * @param by The deadline to complete the task
     */

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     *
     * @return Return a String output for 'deadline' task
     */

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
