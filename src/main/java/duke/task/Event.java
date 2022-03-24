package duke.task;

public class Event extends Task {

    protected String at;

    //example "event read book /at 15th Feb"
    public Event(String description, String at) {
        super(description);
        this.at = at;
        setDT(at);
        System.out.println(this);
        newTask();
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
        setDT(at);
    }

    @Override
    public String addToFile() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getDT() +")";
    }
}