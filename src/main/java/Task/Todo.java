package Task;

public class Todo extends Task {

    public Todo(final String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + this.getStatusIcon() + " " + this.description + " " + (this.isTagged ? "#" : "") + this.tag;
    }
}