public class Event extends Task {
    protected String at;
    protected boolean isEvent;

    public Event(String description, String at) {
        super (description);
        this.at = at;
        isEvent = false;
    }

    public void markAsEvent() {isEvent = true;}

    public void unmarkEvent() {isEvent = false;}

    public String getEventStatusIcon() {
        return (isEvent? "E" : " ");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + at + ")";
    }
}