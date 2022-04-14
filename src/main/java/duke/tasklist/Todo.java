package duke.tasklist;

import java.time.format.DateTimeFormatter;

public class Todo extends Task {

    protected boolean isDone;
    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean newValue) {
        isDone = newValue;
    }
    public Todo(String description) {
        super(description);
        type='T';
    }

 //   @Override
//    public String toString() {
//        String status = null;
//        if (isDone) {
//            status = "Yes";
//        } else {
//            status = "No";
//        }
//        return super.toString() + System.lineSeparator() + "is done? " + status;
//    }
    @Override
    public String toString() {
        return "["+getType()+"]" + getDescription();
    }
}
