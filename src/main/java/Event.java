public class Event extends Task{

    protected String event;

    /**
     * This method takes in 2 parameters and creates an Event.
     * @param description Description of the task
     * @param event Datetime of the task event
     */

    public Event (String description, String event){
        super(description);
        this.event = event;
    }

    public String getEvent(){
        return this.event;
    }

    public void setEvent(String event){
        this.event = event;
    }

    /**
     *
     * @return Return a String for Event
     */

    @Override
    public String toString() {
        return " [E]   " + super.toString() + " (at: " + event + ")";
    }
}
