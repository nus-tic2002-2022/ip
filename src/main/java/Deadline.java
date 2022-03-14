public class Deadline extends Task {

    protected String by;

    //example "deadline read book /by 4:00pm"
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
        newTask();
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}