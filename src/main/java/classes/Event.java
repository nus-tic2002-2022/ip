package classes;

public class Event extends Task {
    private String startTime;

    public Event (String description, String startTime) {
        super(description);
        this.startTime = startTime;
    }

    /**
     * Getter for description
     * **/
    public String getDescription() {
        return description + " (at: " + startTime + ")";
    }

    /**
     * Getter for type of task
     * **/
    public String getType() {
        return "E";
    }
}
