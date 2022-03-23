public class Event extends Task {
    protected boolean isDone;
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    public String getAt(){
        return at;
    }
    public void setAt(String by){
        this.at = by;
    }
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + at + ")";
    }
}
