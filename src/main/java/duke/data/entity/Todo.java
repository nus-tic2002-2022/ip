package duke.data.entity;

/**
 * Extension of Task class to modify Todo tasks.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * @return task in string form
     * e.g. [T][X] go to gym
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * @return task in string form with comman format
     * e.g. T , 1 , go to gym
     */
    @Override
    public String toSaveStr() {
        return "T , " + super.toSaveStr();
    }

}