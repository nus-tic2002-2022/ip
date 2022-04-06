package tasks;

/** A TodoTask object. */
public class Todo extends Task {

    /** Constructor for the Todo Class */
    public Todo(String description) {
        super(description);
    }

    /** Converts the task to a string */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /** Converts the task to a savable format */
    @Override
    public String getSaveFormat() {
        return "T" + super.getSaveFormat() + "| ";
    }
}
