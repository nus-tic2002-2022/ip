package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime dateTime;

    public Event(final String description, final LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E] " + this.getStatusIcon() + " " + this.description + " at: " + dateTime + " " + (this.isTagged ? "#" : "") + this.tag;
    }
}
