

public class Deadline extends Task {

    protected String deadlines;

    public Deadline(String description, String deadlines) {
        super(description);
        this.deadlines = deadlines;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + deadlines + ")";
    }
}

