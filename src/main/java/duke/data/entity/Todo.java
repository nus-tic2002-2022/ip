package duke.data.entity;
/**
 * Extension of Task class to modify Todo tasks.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    /**
     * @return status of Todo with user input and save.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toSaveStr() {
        return "T , " + super.toSaveStr();
    }

}