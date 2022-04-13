public class Todo extends Task{

    protected String todotask;

    /**
     * This method takes in 1 parameter and create the task.
     * @param description Description of the task
     * @param todotask Describes the task
     */

    public Todo (String description, String todotask){
        super(description);
        this.todotask = todotask;
    }

    public String getTodo(){
        return this.todotask;
    }

    public void setTodo(String todotask){
        this.todotask = todotask;
    }

    /**
     *
     * @return Return a String for Todo
     */

    @Override
    public String toString() {
        return " [T]   " + super.toString();
    }
}