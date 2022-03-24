package duke.task;

public class Deadline extends Task {

    protected String by;

    //example "deadline read book /by 4:00pm"
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        setDT(by);
        System.out.println(this);
        newTask();
    }

    public Deadline(String description, String by, boolean isDone){
        super(description, isDone);
        this.by = by;
        setDT(by);
    }

    @Override
    public String addToFile() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDT() + ")";
    }
}