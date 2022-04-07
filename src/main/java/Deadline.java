public class Deadline extends Task {

    protected String by;

    /**
     * This method takes in 2 parameters and create the task.
     * @param description Description of the task
     * @param by Completion Date and time of the task
     */

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy(){
        return this.by;
    }

    public void setBy(String by){
        this.by = by;
    }

    /**
     *
     * @return Return a String for Deadline
     */

    @Override
    public String toString() {
        return " [D]   " + super.toString() + " (by: " + by + ")";
    }
}