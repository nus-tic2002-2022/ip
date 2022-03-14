package  java.duke.task;

public class Event extends Task {

    protected String at;

    //example "event read book /at 15th Feb"
    public Event(String description, String at) {
        super(description);
        this.at = at;
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
        newTask();
    }

    public Event(String description, String at, boolean isDone) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at +")";
    }
}