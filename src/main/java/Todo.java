public class Todo extends Task {

    /**
     * This method takes in 1 parameter and create the task.
     * @param description Description of the task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     *
     * @return Return a String for Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}