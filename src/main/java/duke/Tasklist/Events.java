package duke.Tasklist;

import java.time.format.DateTimeParseException;
import java.util.Date;

public class Events extends Task {

    protected String details;


    public Events(String description, String details) throws DateTimeParseException {
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