package duke.Tasklist;

public class ToDo extends Task {
    protected String by;

    public ToDo (String description)
    {
        super(description);
    }
    public String getStatus()
    {
        return (isDone ? "[T][X] "  + this.description : "[T][ ] " + this.description);
    }
}
