public class Todo extends Task {

    /**
     * This method takes in 1 input parameter and create the 'tod0' task.
     * @param description The task description created
     */

    public Todo(String description) {
        super(description);
    }

    /**
     *
     * @return Return a String output for 'tod0' task
     */

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
