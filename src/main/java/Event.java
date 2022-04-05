public class Event extends Task {

    protected String at;

    /**
     * This method takes in 2 parameters and create the task.
     * @param description Description of the task
     * @param at Completion Datetime of the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     *
     * @return Return a String for Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
