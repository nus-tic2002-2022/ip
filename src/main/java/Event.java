public class Event extends Task {

    protected String at;

    /**
     * This method takes in 2 input parameters and create the 'event' task.
     * @param description The task description created.
     * @param at The date and time of the event task.
     */

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     *
     * @return Return a String output for 'event' task
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
