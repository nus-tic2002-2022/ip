package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime dateTime;

    public Deadline(final String description, final LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }



    @Override
    public String toString() {
        return "[D] " + this.getStatusIcon() + " " + this.description + " by: " + dateTime + " " + (this.isTagged ? "#" : "") + this.tag;
    }
}