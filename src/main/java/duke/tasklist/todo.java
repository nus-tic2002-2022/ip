package duke.tasklist;

public class todo extends task {

    protected boolean isDone;
    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean newValue) {
        isDone = newValue;
    }
    public todo(String description) {
        super(description);
        type='T';
    }

    @Override
    public String toString() {
        return "["+getType()+"]" + getDescription();
    }
}
