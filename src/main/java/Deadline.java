public class Deadline extends Task {
    protected String by;
    protected boolean isDeadline;

    public Deadline(String description, String by) {
        super (description);
        this.by = by;
        isDeadline = false;
    }

    public void markAsDeadline() {isDeadline = true;}

    public void unmarkTodo() {isDeadline = false;}

    public String getDeadlineStatusIcon() {
        return (isDeadline? "D" : " ");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
