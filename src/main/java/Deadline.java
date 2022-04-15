

public class Deadline extends Task {

    protected String deadlines;

    public Deadline(String description, String deadlines) {
        super(description);
        this.deadlines = deadlines;
        System.out.println("Got it. I've added this task:");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + deadlines + ")";
    }
}

