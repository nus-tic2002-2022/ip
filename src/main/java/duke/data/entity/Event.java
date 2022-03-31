package duke.data.entity;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String toSaveStr() {
        return "E , " + super.toSaveStr() + " , " + at;
    }

}