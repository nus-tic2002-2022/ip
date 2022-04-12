package tasks;

public class FixedDuration extends Task {
    protected int duration;

    /** Constructor for the Fixed Duration Class */
    public FixedDuration(String description, int duration) {
        super(description);
        this.duration = duration;
    }

    /** Converts the task to a string */
    @Override
    public String toString() {
        return "[F]" + super.toString() + " (needs " + duration + " hours)";
    }

    /** Converts the task to a savable format */
    @Override
    public String getSaveFormat() {
        return "F" + super.getSaveFormat() + "|" + duration;
    }

}
