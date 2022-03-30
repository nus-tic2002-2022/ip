package duke.Tasklist;

public class Events extends Task {

    protected String details;


    public Events(String description, String details) {
        super(description);
        this.details = details;

    }

    public String getStatus() {
        return (isDone ? "[E][X] " + this.description + "( " + details + ")": "[E][ ] " + this.description + " (" + details + ")");
    }
    public String getDetails()
    {
        return details;
    }
}