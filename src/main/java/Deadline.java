/**
 * Creates a Deadline object. Deadline object is inherited from Task object.
 */

public class Deadline extends Task {

    protected String deadlines;

    public Deadline(String description, String deadlines) {
        super(description);
        this.deadlines = deadlines;
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
        newTask();
    }

    //Overload
    public Deadline(String description, String deadlines, boolean isDone) {
        super(description);
        this.deadlines = deadlines;
        this.isDone = isDone;
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + deadlines + ")";
    }
}

