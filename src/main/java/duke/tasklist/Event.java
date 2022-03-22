package duke.tasklist;

/**
 * This class is an extension of Task class with the addition of "at" variable and also contains methods to access and modify variable.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor for Event
     *
     * @param description description of Event.
     * @param at duration of Event.
     */
    public Event(String description, String at) {
        super(description);
        this.description = description;
        this.at = at;
    }

    /**
     * Returns duration of Event.
     *
     * @return duration of Event in String format.
     */
    public String getAt(){
        return this.at;
    }

    /**
     * Update duration of Event with duration provided.
     *
     * @param at duration of Event to update as.
     */
    public void setAt(String at){
        this.at = at;
    }

    @Override
    public String toString() {
        String output = super.toString();
       return output.substring(0,1) + 'E' + output.substring(2) + "\tat : " + at;
    }
}
